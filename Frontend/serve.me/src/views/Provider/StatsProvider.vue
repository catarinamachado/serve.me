<template>
  <div class="stats space-top-5 space-bottom-6 space-left-right-5">
    <div class="container">
      <h4 class="space-bottom-5">Estatísticas do ano {{ano}}</h4>
      <div class="row space-bottom-5">
          <div class="col-6">
              <label class="border"><strong>Ganho em {{ano}}:</strong> {{ganho_anual}}€</label>
          </div>
          <div class="col-6">
              <label class="border"><strong>N.º serviços realizados em {{ano}}:</strong> {{nr_servicos}}</label>
          </div>
      </div>

      <div class="row justify-content-between">
        <div class="col-md-6">
          <h5><strong>Ganho/mês</strong></h5>        
          <template>
            <mdb-container>
              <mdb-bar-chart
                :data="barChartData"
                :options="barChartOptions"
                :width="500"
                :height="300"
              ></mdb-bar-chart>
            </mdb-container>
          </template>
        </div>
        <div class="col-md-6">
          <h5><strong>Serviços/mês</strong></h5>        
          <template>
            <mdb-container>
              <mdb-line-chart
                :data="lineChartData"
                :options="lineChartOptions"
                :width="500"
                :height="300"
              ></mdb-line-chart>
            </mdb-container>
          </template>
        </div>
      </div>

      <div class="row d-flex justify-content-center space-top-8">
        <div>
        <h5><strong>N.º de Serviços por Categoria</strong></h5>
          <template>
          <mdb-container>
            <mdb-pie-chart
            datalabels
            :data="pieChartData"
            :options="pieChartOptions"
            :width="800"
            :height="450"
            />
          </mdb-container>
          </template>
        </div>    
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>

<script>
  import { mdbPieChart, mdbBarChart, mdbLineChart, mdbContainer } from "mdbvue";

  export default {
    name: "stats-provider",
    created() {
      window.scrollTo(0, 0)
      this.stats()
    },    
    components: {
      mdbPieChart,
      mdbBarChart,
      mdbLineChart,
      mdbContainer
    },
    methods: {
      stats(){
        this.$axios({url: this.$backend + '/stats/my-stats', method: 'GET',
          headers: {
          'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
          }}).then(resp => {
            //Ano atual
            this.ano = resp.data.ano
            
            //Labels
            this.ganho_anual = resp.data.ganho_anual
            this.nr_servicos = resp.data.servicos_anual

            //Meses
            const ganhos_mes = resp.data.ganhos_por_mes.values()
            var meses = []
            for (const value of ganhos_mes) {
              meses.push(value.eixo_x)
            }

            //Categorias
            const servicos_categoria = resp.data.servicos_por_subcat.values()
            var categorias = []
            for (const value of servicos_categoria) {
              categorias.push(value.eixo_x)
            }            

            //Serviços por categoria
            const servs_categs = resp.data.servicos_por_subcat.values()
            var nrs_servicos_categ = []
            for (const value of servs_categs) {
              nrs_servicos_categ.push(value.eixo_y)
            }

            this.pieChartData = {
              labels: categorias,
              datasets: [
                {
                  data: nrs_servicos_categ,
                  backgroundColor: [
                    "#fab1a0",
                    "#00b894",
                    "#ff7675",
                    "#6c5ce7",
                    "#ffeaa7",
                    "#b2bec3",
                    "#0984e3",
                    "#00cec9",
                    "#fd79a8"
                  ],
                  hoverBackgroundColor: [
                    "#FBC4B8",
                    "#40CAAF",
                    "#FF9898",
                    "#9185ED",
                    "#FFEFBD",
                    "#C5CED2",
                    "#47A3EA",
                    "#40DAD7",
                    "#FE9BBE"                
                  ]
                }
              ]
            }            

            //Ganhos por mês
            const gan_mes = resp.data.ganhos_por_mes.values()
            var nrs_ganhos = []
            for (const value of gan_mes) {
              nrs_ganhos.push(value.eixo_y)
            }

            this.barChartData = {
              labels: meses,
              datasets: [
                {
                  label: "Ganho (€)",
                  data: nrs_ganhos,
                  backgroundColor: [
                    "rgba(255, 99, 132, 0.2)",
                    "rgba(54, 162, 235, 0.2)",
                    "rgba(255, 206, 86, 0.2)",
                    "rgba(75, 192, 192, 0.2)",
                    "rgba(153, 102, 255, 0.2)",
                    "rgba(255, 159, 64, 0.2)",
                    "rgba(255, 99, 132, 0.2)",
                    "rgba(54, 162, 235, 0.2)",
                    "rgba(255, 206, 86, 0.2)",
                    "rgba(75, 192, 192, 0.2)",
                    "rgba(153, 102, 255, 0.2)",
                    "rgba(255, 159, 64, 0.2)"                
                  ],
                  borderColor: [
                    "rgba(255,99,132,1)",
                    "rgba(54, 162, 235, 1)",
                    "rgba(255, 206, 86, 1)",
                    "rgba(75, 192, 192, 1)",
                    "rgba(153, 102, 255, 1)",
                    "rgba(255, 159, 64, 1)",
                    "rgba(255,99,132,1)",
                    "rgba(54, 162, 235, 1)",
                    "rgba(255, 206, 86, 1)",
                    "rgba(75, 192, 192, 1)",
                    "rgba(153, 102, 255, 1)",
                    "rgba(255, 159, 64, 1)"                
                  ],
                  borderWidth: 1
                }
              ]
            }

            //Serviços por mês
            const serv_mes = resp.data.servicos_por_mes.values()
            var nrs_serv_mes = []
            for (const value of serv_mes) {
              nrs_serv_mes.push(value.eixo_y)
            }

            this.lineChartData = {
              labels: meses,
              datasets: [
                {
                  label: "N.º de serviços",
                  backgroundColor: "rgba(255, 206, 86, 0.2)",
                  borderColor: "rgba(255, 206, 86, 1)",
                  borderWidth: 0.7,
                  data: nrs_serv_mes
                }
              ]
            }
        })
      }
    },
    data() {
      return {
        ano: '',
        ganho_anual: '',
        nr_servicos: '',
        pieChartData: {
          labels: [],
          datasets: []
        },
        pieChartOptions: {
          responsive: false,
          maintainAspectRatio: false,
          plugins: {
            datalabels: {
              color: "white",
              align: "center",
              font: {
                size: 16
              },
              formatter: value => {
                const [dataset] = this.pieChartData.datasets;
                const setValue = dataset.data.reduce((a, b) => a + b);

                return `${Math.round((value / setValue) * 100)}%`;
              }
            }
          }
        },
        barChartData: {
          labels: [],
          datasets: []
        },
        barChartOptions: {
          responsive: false,
          maintainAspectRatio: false,
          scales: {
            xAxes: [
              {
                barPercentage: 1,
                gridLines: {
                  display: true,
                  color: "rgba(0, 0, 0, 0.1)"
                }
              }
            ],
            yAxes: [
              {
                gridLines: {
                  display: true,
                  color: "rgba(0, 0, 0, 0.1)"
                }
              }
            ]
          }
        },
        lineChartData: {
          labels: [],
          datasets: []
        },
        lineChartOptions: {
          responsive: false,
          maintainAspectRatio: false,
          scales: {
            xAxes: [
              {
                gridLines: {
                  display: true,
                  color: "rgba(0, 0, 0, 0.1)"
                }
              }
            ],
            yAxes: [
              {
                gridLines: {
                  display: true,
                  color: "rgba(0, 0, 0, 0.1)"
                }
              }
            ]
          }
        }
      };
    }
  };
</script>
