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
        <h5><strong>N.º de Serviços por Subcategoria</strong></h5>
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
      window.scrollTo(0, 0);
    },    
    components: {
      mdbPieChart,
      mdbBarChart,
      mdbLineChart,
      mdbContainer
    },
    data() {
      return {
        ano: 2020,
        ganho_anual: 1003,
        nr_servicos: 109,       
        pieChartData: {
          labels: ["Vedação para Jardim", "Decoração de Jardins", "Manutenção de Canteiros",
                   "Corte de Árvores", "Remoção de Ervas Daninhas","Preparação do Solo para Jardinagem",
                   "Limpeza de Jardim","Plantação de Árvores","Outro"],
          datasets: [
            {
              data: [30, 21, 8, 6, 11, 21, 6, 10, 20],
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
          labels: [
            "jan",
            "fev",
            "mar",
            "abr",
            "maio",
            "jun",
            "jul",
            "ago",
            "set",
            "out",
            "nov",
            "dez"
          ],
          datasets: [
            {
              label: "Ganho (€)",
              data: [12, 19, 3, 5, 6, 3, 10, 8, 4, 15, 8, 14],
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
          labels: [
            "jan",
            "fev",
            "mar",
            "abr",
            "maio",
            "jun",
            "jul",
            "ago",
            "set",
            "out",
            "nov",
            "dez"
          ],
          datasets: [
            {
              label: "N.º de serviços",
              backgroundColor: "rgba(255, 206, 86, 0.2)",
              borderColor: "rgba(255, 206, 86, 1)",
              borderWidth: 0.7,
              data: [65, 59, 80, 81, 56, 55, 40, 10, 34, 12, 9, 79]
            }
          ]
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
