package EA_ServeMe.beans;

import EA_ServeMe.util.DateUtils;
import EA_ServeMe.util.Log;
import EA_ServeMe.util.ProposeProvider;
import EA_ServeMe.util.RequestResponse;
import categorias.Categoria;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.context.annotation.Bean;
import servico.Pedido;
import servico.PedidoDAO;
import servico.Proposta;
import servico.PropostaDAO;
import utilizador.Cliente;
import utilizador.ClienteDAO;
import utilizador.Prestador;
import utilizador.PrestadorDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Prestador_Services {
    private static final String TAG =  "[PRESTADORSERVICES]";

    @Bean
    public static List<RequestResponse> getRequests() {
        List<RequestResponse> res = new ArrayList<>();
        Date now = new Date();
        String query = "Estado < 1 ";
        List<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = Arrays.asList(PedidoDAO.listPedidoByQuery(query,"HoraInicioDisp"));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        if(pedidos.size()  == 0 ){
            Log.w(TAG,"Requests - Nothing to Show");
            return res;
        }
        for (Pedido p: pedidos) {
            if (now.before(p.getHoraFimDisp())) {
                RequestResponse tmp = new RequestResponse();
                tmp.asResponse(p);
                res.add(tmp);
            }
        }
        Log.i(TAG,"Services Loaded Sucessfully");
        return res;
    }

    @Bean
    public static List<String> addPropose(String propose, String email) {
        List<String> error = new ArrayList<>();
        List<String> success = new ArrayList<>();
        error.add("Error:");
        success.add("OK");

        // Get prestador
        Prestador prestador = Prestador_Perfil.getPrestadorbyEmail(email);

        try {
            Proposta proposta = parseProposeJSON(propose,prestador);
            PropostaDAO.save(proposta);
            Log.i(TAG,"Propose Added Succesfully");
            return success;

        } catch (Exception e) {
            String type = e.getMessage();
            String[] split = type.split("-");
            for (String err: split
            ) {
                int tipo = Integer.valueOf(err);
                switch (tipo) {
                    case 1:
                        Log.e(TAG,"DataInicio not Valid" + " - Propose not Added");
                        error.add("DataInicio");
                        break;
                    case 2:
                        Log.e(TAG,"DataInicio off valid interval" + " - Propose not Added");
                        error.add("DataInicio");
                        break;
                    case 3:
                        error.add("JSON");
                        Log.e(TAG,"Missing Fields on JSON" + " - Propose not Added");
                        break;
                    case 4:
                        Log.e(TAG,"Pedido doesn't recieve offers" + " - Propose not Added");
                        error.add("Pedido");
                        break;
                    case 5:
                        Log.e(TAG,"Pedido doesn't exist" + " - Propose not Added");
                        error.add("Pedido");
                        break;
                }
            }
        }
        return error;
    }

    private static Proposta parseProposeJSON(String propose, Prestador prestador) throws Exception {
        JSONObject obj = new JSONObject(propose);
        String err = "";
        // Vars needed
        int p_ID = 0;
        Pedido pedido;
        double preco = 0.0;
        Date dataInicio = new Date();

        // Parse fase
        try {
            p_ID = obj.getInt("id_pedido");
            pedido = PedidoDAO.getPedidoByORMID(p_ID);
            preco = obj.getDouble("preco");
            String s_dataInicio = obj.getString("dataInicio");
            dataInicio = DateUtils.toDate(s_dataInicio);

        }catch (PersistentException pe){
            err+="5-";
            throw new Exception(err);
        } catch (Exception e){
            err+="3-";
            throw new Exception(err);
        }
//          Verificacao de Datas e pedido


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inicio_proposto = DateUtils.asLocalDateTime(dataInicio);
        LocalDateTime inicio_pedido = DateUtils.asLocalDateTime(pedido.getHoraInicioDisp());
        LocalDateTime fim_pedido = DateUtils.asLocalDateTime(pedido.getHoraFimDisp());


        if (inicio_proposto.isBefore(now)) err += "1-";
        else {
            if (inicio_proposto.isBefore(inicio_pedido) || inicio_proposto.isAfter(fim_pedido)) err += "2-";
        }
        if (pedido.getEstado() > 0) err += "4-";

        if(!err.equals("")){
            err = err.substring(0,err.length()-1);
            throw new Exception(err);
        }
        return buildProposta(prestador,pedido, preco, dataInicio);
    }

    private static Proposta buildProposta(Prestador prestador, Pedido pedido, double preco, Date dataInicio) {
        Proposta proposta = PropostaDAO.createProposta();
        proposta.setPedido(pedido);proposta.setHoraInicio(dataInicio);
        proposta.setPrecoProposto(preco);proposta.setPrestador(prestador);
        proposta.setVencedora(0);
        return proposta;
    }

    @Bean
    public static List<ProposeProvider> getMyProposes(String email) {
        List<ProposeProvider> r = new ArrayList<>();
        int id = Prestador_Perfil.getPrestadorbyEmail(email).getID();
        String query = "PrestadorID = " + id;

        try {
            List<Proposta> propostas = Arrays.asList(PropostaDAO.listPropostaByQuery(query,"HoraInicio"));
            for (Proposta tmp : propostas){
                ProposeProvider pp = new ProposeProvider().asResponse(tmp);
                r.add(pp);
            }

        } catch (PersistentException e) {
            Log.e(TAG,"BD problem");
        }

        if(r.size() == 0)
            Log.w(TAG, "Proposes - Nothing to show!");
        else
            Log.i(TAG,"Proposes Loaded Succesfully");

        return r;
    }
}
