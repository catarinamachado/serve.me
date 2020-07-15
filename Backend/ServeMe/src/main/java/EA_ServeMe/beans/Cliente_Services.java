package EA_ServeMe.beans;

import EA_ServeMe.responses.InboxResponse;
import EA_ServeMe.responses.ProposeResponse;
import EA_ServeMe.responses.RequestResponse;
import EA_ServeMe.responses.ServiceResponse;
import EA_ServeMe.util.*;
import categorias.Categoria;
import categorias.CategoriaDAO;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.context.annotation.Bean;
import servico.*;
import utilizador.Cliente;
import utilizador.ClienteDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Cliente_Services {
    private static final String TAG =  "[CLIENTESERVICES]";

    @Bean
    public static List<String> addRequest(String request,String email) {
        List<String> resp = new ArrayList<>();
        resp.add("Error");

        /* Get Cliente */

        Cliente c;
        c = Cliente_Perfil.getClientebyEmail(email);

        /* Process and Save 'Pedido' */

        List<String> success = new ArrayList<>();
        success.add("OK");
        try { // PROD: ADD THIS
            Pedido p = parsePedido(request, c);
            if(p!=null){
                PedidoDAO.save(p);
                Log.i(TAG,"Request Added Succesfully");
                return success;
            }
        }catch (PersistentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            String type = e.getMessage();
            String[] split = type.split("-");
            for (String err: split
                 ) {
                int tipo = Integer.valueOf(err);
                switch (tipo) {
                    case 1:
                        Log.e(TAG,"DataFim not Valid" + " - Request not Added");
                        resp.add("O horário indicado  é inválido.");
                        break;
                    case 2:
                        Log.e(TAG,"duracao is lower than needed" + " - Request not Added");
                        resp.add("A duração do pedido não é válida.");
                        break;
                    case 3:
                        resp.add("Pedido mal formulado!");
                        Log.e(TAG,"Missing Fields on JSON" + " - Request not Added");
                        break;
                    case 4:
                        Log.e(TAG,"Categoria does not Exist" + " - Request not Added");
                        resp.add("Categoria não encontrada.");
                        break;
                }
            }
        }
        return resp;
    }

    private static Pedido parsePedido(String request,Cliente c) throws Exception {
        JSONObject obj = new JSONObject(request);
        String err = "";
        String categoria = "";
        double preco = 0.0;
        Date dataInicio = new Date();
        Date dataFim = new Date();
        double dur = 0.0;
        String desc = "";
        Categoria cat = null;
        try {
            categoria = obj.getString("categoria");
            cat = str2cat(categoria);

            preco = Double.valueOf(obj.getString("preco"));

            String s_dataInicio = obj.getString("dataInicio");
            dataInicio = DateUtils.toDate(s_dataInicio);


            String s_dataFim = obj.getString("dataFim");
            dataFim = DateUtils.toDate(s_dataFim);

            dur = Double.valueOf(obj.getDouble("duracao"));

            desc = obj.getString("descricao");

        }catch (Exception e){
            err+="3-";
            throw new Exception(err);
        }
//          Verificacao de Datas e Categoria

        Date now = new Date();
        LocalDateTime inicio = DateUtils.asLocalDateTime(dataInicio);
        LocalDateTime fim = DateUtils.asLocalDateTime(dataFim);
        long duracao_h = (long) dur;
        String s_dur = dur + "";

        long duracao_m =(long) Long.valueOf(s_dur.split("\\.")[1]);
        if (dataFim.before(dataInicio) || dataFim.before(now)) err += "1-";
        else {
            if (inicio.plusHours(duracao_h).plusMinutes(duracao_m).isAfter(fim)) err += "2-";
        }
        if (cat == null) err += "4-";
        if(!err.equals("")){
            err = err.substring(0,err.length()-1);
            throw new Exception(err);
        }
        return buildPedido(c, cat, preco, dataInicio, dataFim, dur, desc);
    }

    private static Pedido buildPedido(Cliente c, Categoria cat, double preco, Date dataInicio, Date dataFim, double dur, String desc) {
        Pedido p = new Pedido();
        p.setCliente(c);p.setCategoria(cat);p.setPrecoHora(preco);
        p.setData(DateUtils.asDate(DateUtils.asLocalDate(dataInicio)));p.setHoraInicioDisp(dataInicio);p.setHoraFimDisp(dataFim);p.setDuracao(dur);
        p.setDescricao(desc);p.setEstado(PedidoState.WAIT.v());
        return p;
    }

    private static Categoria str2cat(String categoria) {
        String q = "Nome = '" +  categoria +"'";
        try {
            List<Categoria> cat = Arrays.asList(CategoriaDAO.listCategoriaByQuery(q, "Nome"));
            if (cat.size() > 0)
                return cat.get(0);
        } catch (PersistentException e) {
        }
        return null;
    }

    @Bean
    public static List<RequestResponse> getRequests(String email) {
        List<RequestResponse> res = new ArrayList<>();
        Date now = new Date();
        LocalDateTime ldtnow = LocalDateTime.now();
        int ID = Cliente_Perfil.getClientebyEmail(email).getID();
        String query = "ClienteID = " + ID;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = Arrays.asList(PedidoDAO.listPedidoByQuery(query,"HoraInicioDisp"));
            for(Pedido p : pedidos){
                if(DateUtils.asLocalDateTime(p.getHoraFimDisp()).isBefore(LocalDateTime.now()) && p.getEstado() < PedidoState.SERVICE.v() && p.getEstado() != PedidoState.CANCELLED.v()){
                    p.setEstado(PedidoState.EXPIRED.v());
                    PedidoDAO.save(p);
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        if(pedidos.size()  == 0 ){
            Log.w(TAG,"Requests - Nothing to Show");
            return res;
        }
        for (Pedido p: pedidos) {
                RequestResponse tmp = new RequestResponse();
                tmp.asResponse(p);
                res.add(tmp);
        }
        Log.i(TAG,"Cliente Services Loaded Sucessfully");
        return res;

    }

    @Bean
    public static List<String> editRequest(String request, String email) {
        List<String> resp = new ArrayList<>();
        resp.add("Error");
        /* Verify request to edit */
        JSONObject obj = new JSONObject(request);
        int id = obj.getInt("id");


        /* Get Cliente  */

        Cliente c;
        c = Cliente_Perfil.getClientebyEmail(email);

        /* Process and Update 'Pedido' */

        List<String> success = new ArrayList<>();
        success.add("OK");
        try { // PROD: ADD THIS
            Pedido p = parsePedido(request, c);
            if(p!=null){
                Pedido op = PedidoDAO.getPedidoByORMID(id);
                if(op.getEstado() <= 0 && op.getCliente().getEmail().equals(email)) {
                    op.setPrecoHora(p.getPrecoHora());
                    op.setDuracao(p.getDuracao());
                    /* PROD: ADD THIS - Need Frontend changes
                    op.setData(p.getData());
                    op.setHoraInicioDisp(p.getHoraInicioDisp());
                    op.setHoraFimDisp(p.getHoraFimDisp());
                    op.setDescricao(p.getDescricao());
                     */
                    PedidoDAO.save(op);
                    Log.i(TAG,"Request Edited Succesfully");
                    return success;
                }
                else{

                    if(op.getEstado() > 0) {
                        resp.add("Estado");
                        Log.e(TAG,"Impossible Edit Request - Invalid State");
                    }
                    if(!op.getCliente().getEmail().equals(c.getEmail())){
                        resp.add("Cliente");
                        Log.e(TAG,"Impossible Edit Request - Invalid Cliente");
                    }
                }
            }
        }catch (PersistentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            String type = e.getMessage();
            String[] split = type.split("-");
            for (String err: split
            ) {
                int tipo = Integer.valueOf(err);
                switch (tipo) {
                    case 1:
                        Log.e(TAG,"DataFim not Valid" + " - Request not Added");
                        resp.add("DataFim");
                        break;
                    case 2:
                        Log.e(TAG,"duracao is lower than needed" + " - Request not Added");
                        resp.add("Duracao");
                        break;
                    case 3:
                        resp.add("JSON");
                        Log.e(TAG,"Missing Fields on JSON" + " - Request not Added");
                        break;
                    case 4:
                        Log.e(TAG,"Categoria does not Exist" + " - Request not Added");
                        resp.add("Categoria");
                        break;
                }
            }
        }
        return resp;
    }

    @Bean
    public static List<String> deleteRequest(String request, String email) {
        List<String> resp = new ArrayList<>();
        resp.add("Error");
        /* Verify request to edit */
        JSONObject obj = new JSONObject(request);
        int id = obj.getInt("id");

        /* Ensure Cliente exists */

        Cliente c;
        c = Cliente_Perfil.getClientebyEmail(email);

        /* Process and Save 'Pedido' */

        List<String> success = new ArrayList<>();
        success.add("OK");

        try {
            Pedido p = PedidoDAO.getPedidoByORMID(id);
            if(p.getEstado() <= 0  && p.getCliente().getEmail().equals(c.getEmail())){
                p.setEstado(-100);
                PedidoDAO.save(p);
                Log.i(TAG,"Request Cancelled Succesfully");
                return success;
            }
            else {
                if (p.getEstado() > 0){
                    resp.add("Estado");
                    Log.e(TAG,"Impossible Edit Request - Invalid State");
                }
                else {
                    resp.add("Cliente");
                    Log.e(TAG,"Impossible Edit Request - Invalid Cliente");
                }
            }

        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Bean
    public static List<String> acceptPropose(String propose, String email) {
        List<String> error = new ArrayList<>();
        List<String> success = new ArrayList<>();
        error.add("Error:");
        success.add("OK");

//        Needed Vars
        int id_proposta = -1;
        Proposta proposta;

//        Get propose ID
        JSONObject obj = new JSONObject(propose);
        id_proposta = obj.getInt("id_proposta");

        if(id_proposta == -1 ){
            Log.e(TAG,"JSON Missing fields");
            error.add("JSON");
            return error;
        }
        try {
            proposta = PropostaDAO.getPropostaByORMID(id_proposta);
            Pedido pedido = proposta.getPedido();
            Cliente cliente = pedido.getCliente();
//            Verificaçãões necessárias
            if (!cliente.getEmail().equals(email)){ //Verificação do Ownership do Pedido
                error.add("Pedido");
                Log.e(TAG,"Request Owner invalid");
                return error;
            }

            if(pedido.getEstado() > 0){ //Verficiação de +1 vencedores
                error.add("Pedido");
                Log.e(TAG,"Request Already have a Winner");
                return error;
            }


//            Build Servico
            Servico servico = buildServico(cliente,pedido,proposta);
//            Guardar o Servico
            ServicoDAO.save(servico);
//            Update States
            pedido.setEstado(PedidoState.SERVICE.v());
            proposta.setVencedora(PropostaState.WINNER.v());
//            Save new states
            PedidoDAO.save(pedido);
            PropostaDAO.save(proposta);

            Log.i(TAG,"Propose Accepted Succesfully - Service Scheduled");
            return success;

        } catch (PersistentException e) {
            error.add("BD");
        }
        return error;
    }

    private static Servico buildServico(Cliente cliente, Pedido pedido, Proposta proposta) {
        Servico servico = ServicoDAO.createServico();
        servico.setCliente(cliente);servico.setPrestador(proposta.getPrestador());
        servico.setPedido(pedido);servico.setProposta(proposta);
        servico.setEstado(ServicoState.CREATED.v());
        return servico;
    }

    @Bean
    public static List<ProposeResponse> getRecievedProposes(String email, String idJSON) {
        List<ProposeResponse> r = new ArrayList<>();
        int id_pedido = -1;
        int id_cliente = Cliente_Perfil.getClientebyEmail(email).getID();
        JSONObject obj = new JSONObject(idJSON);
        id_pedido = obj.getInt("id_pedido");
        if (id_pedido == -1){
            Log.e(TAG,"Request has invalid owner");
            return r;
        }
        String query = "PedidoID = " + id_pedido;

        try {
            List<Proposta> propostas =  Arrays.asList(PropostaDAO.listPropostaByQuery(query,"HoraInicio"));
            if(propostas.size() == 0 ){
                Log.w(TAG,"There's no offers for this request");
                return r;
            }
            for(Proposta tmp : propostas ) {
                ProposeResponse pr =  new ProposeResponse().asResponse(tmp);
                r.add(pr);
            }
            Log.i(TAG,"Proposes Loaded Succesfully");
            return r;
        } catch (PersistentException e) {
           Log.e(TAG,"BD error");
           return r;
        }
    }

    @Bean
    public static List<ServiceResponse> getMyServices(String email) {
        List<ServiceResponse> r = new ArrayList<>();
        int id_cliente = Cliente_Perfil.getClientebyEmail(email).getID();
        String query = "ClienteID = " + id_cliente;

        List<Servico> servicos = new ArrayList<>();
        try {
            servicos = Arrays.asList(ServicoDAO.listServicoByQuery(query,"ClienteID"));
            if (servicos.size() == 0){
                Log.w(TAG,"There's no Services for this Client");
                return r;
            }
            for (Servico tmp :
                   servicos) {
                ServiceResponse sr = new ServiceResponse().asResponse(tmp);
                r.add(sr);
            }
            Log.i(TAG,"Services Loaded Succesfully");
            return r;
        } catch (PersistentException e) {
            Log.e(TAG,"BD error");
            return r;
        }
    }

    @Bean
    public static List<ServiceResponse> getScheduledServices(String email) {
        List<ServiceResponse> r = new ArrayList<>();
        int id_cliente = Cliente_Perfil.getClientebyEmail(email).getID();
        int estado = ServicoState.CREATED.v();
        String query = "ClienteID = " + id_cliente + " AND " + "Estado = " + estado;
        try {
            List<Servico> servicos = Arrays.asList(ServicoDAO.listServicoByQuery(query,"ClienteID"));
            if (servicos.size() == 0){
                Log.w(TAG,"There's no Scheduled Services for this Client");
                return r;
            }
            for (Servico tmp :
                    servicos) {
                ServiceResponse sr = new ServiceResponse().asResponse(tmp);
                r.add(sr);
            }
            Log.i(TAG,"Scheduled Services Loaded Succesfully");
            return r;
        } catch (PersistentException e) {
            Log.e(TAG,"BD error");
            return r;
        }
    }


    @Bean
    public static List<ServiceResponse> getCompletedServices(String email) {
        List<ServiceResponse> r = new ArrayList<>();
        int id_cliente = Cliente_Perfil.getClientebyEmail(email).getID();
        int estado = ServicoState.CLIENTDONE.v();
        String query = "ClienteID = " + id_cliente + " AND " + "Estado >= " + estado;
        try {
            List<Servico> servicos = Arrays.asList(ServicoDAO.listServicoByQuery(query,"ClienteID"));
            if (servicos.size() == 0){
                Log.w(TAG,"There's no Completed Services for this Client");
                return r;
            }
            for (Servico tmp :
                    servicos) {
                ServiceResponse sr = new ServiceResponse().asResponse(tmp);
                r.add(sr);
            }
            Log.i(TAG,"Completed Services Loaded Succesfully");
            return r;
        } catch (PersistentException e) {
            Log.e(TAG,"BD error");
            return r;
        }
    }

    @Bean
    public static List<ServiceResponse> getNextServices(String email) {
        List<ServiceResponse> r = new ArrayList<>();
        int id_cliente = Cliente_Perfil.getClientebyEmail(email).getID();
        int estado = ServicoState.CREATED.v();
        String query = "ClienteID = " + id_cliente + " AND " + "Estado = " + estado;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime week = now.minusWeeks(1);
        try {
            List<Servico> servicos = Arrays.asList(ServicoDAO.listServicoByQuery(query,"ClienteID"));
            if (servicos.size() == 0){
                Log.w(TAG,"There's no Next Week Services for this Client");
                return r;
            }
            for (Servico tmp :
                    servicos) {
                LocalDateTime hora = DateUtils.asLocalDateTime(tmp.getProposta().getHoraInicio());
                if(hora.isAfter(now) && hora.isBefore(week)) {
                    ServiceResponse sr = new ServiceResponse().asResponse(tmp);
                    r.add(sr);
                }
            }
            Log.i(TAG,"Next Week Services Loaded Succesfully");
            return r;
        } catch (PersistentException e) {
            Log.e(TAG,"BD error");
            return r;
        }
    }

    @Bean
    public static List<String> cancelService(String email, String idJSON) {
        List<String> error = new ArrayList<>();
        List<String> success = new ArrayList<>();
        error.add("Error:");
        success.add("OK");
        Cliente cliente = Cliente_Perfil.getClientebyEmail(email);
//        Get id of the Service
        int servico_ID = -1;
        JSONObject obj = new JSONObject(idJSON);
        servico_ID = obj.getInt("id_servico");

        if (servico_ID == -1){
            error.add("JSON");
            Log.e(TAG,"Missing field in JSON");
        }

        try {
            Servico servico = ServicoDAO.getServicoByORMID(servico_ID);
            if(servico.getEstado() >= ServicoState.CLIENTDONE.v() ){
                error.add("Servico Evaluated");
                Log.e(TAG,"Impossible to Cancel Service - It's already Done");
                return error;
            }
            servico.setEstado(ServicoState.CLIENTCANCELLED.v());
            ServicoDAO.save(servico);
        } catch (PersistentException e) {
            error.add("servico");
            Log.e(TAG,"Error Cancelling the Service");
            return error;
        }

        try{
            int num_cancelados = cliente.getNumServicosCancelados();
            cliente.setNumServicosCancelados(num_cancelados+1);
            ClienteDAO.save(cliente);
            Log.i(TAG,"Service Cancelled Succesfully");
            return success;
        } catch (PersistentException e) {
            error.add("Cliente");
            Log.e(TAG,"Error Updating the Client");
            return error;
        }
    }

    @Bean
    public static List<InboxResponse> getInbox(String email) {
        List<InboxResponse> ibrs = new ArrayList<>();
        int clienteID = Cliente_Perfil.getClientebyEmail(email).getID();

        /*Search for new proposes*/
        try {
            String query1 = "ClienteID = " + clienteID + " AND " +  "Estado = " + PedidoState.RESPONDED.v();
            List<Pedido> pedidos = Arrays.asList(PedidoDAO.listPedidoByQuery(query1,"ID"));
            for (Pedido tmp :
                    pedidos) {
                int pedidoID = tmp.getID();
                String query2 = "PedidoID = " + pedidoID + " AND " + "Vencedora = " + PropostaState.UNSEEN.v() ;
                List<Proposta> propostas = Arrays.asList(PropostaDAO.listPropostaByQuery(query2,"ID"));
                for (Proposta p:
                     propostas) {
                    InboxResponse ibr = new InboxResponse(p.getID(),p.getPrestador().getNome(),p.getPrestador().getEmail(),tmp.getDescricao(),tmp.getCategoria().getClasse().getNome(),tmp.getCategoria().getNome(),
                            DateUtils.asString(tmp.getData(),0),DateUtils.asString(p.getHoraInicio(),1),tmp.getDuracao(),p.getPrecoProposto(),1);
                    ibrs.add(ibr);
                }
            }
        } catch (PersistentException e) {
            Log.e(TAG,"BD error");
        }

        /*Search for cancelled Services*/
        try {
            String query3 = "ClienteID = " + clienteID + " AND " + "Estado = " + ServicoState.PROVIDERCANCELLED.v();
            List<Servico> servicos = Arrays.asList(ServicoDAO.listServicoByQuery(query3,"ID"));
            for (Servico s :
                    servicos) {
                InboxResponse ibr = new InboxResponse(s.getID(),s.getPrestador().getNome(),s.getPrestador().getEmail(),s.getPedido().getDescricao(),s.getPedido().getCategoria().getClasse().getNome(),
                        s.getPedido().getCategoria().getNome(),DateUtils.asString(s.getProposta().getHoraInicio(),0),DateUtils.asString(s.getProposta().getHoraInicio(),1),s.getPedido().getDuracao(),
                        s.getProposta().getPrecoProposto(),-1);
                ibrs.add(ibr);
            }
        } catch (PersistentException e) {
            Log.e(TAG,"BD error");
        }

        /*Search for unevaluated services*/
        try {
            String query3 = "ClienteID = " + clienteID + " AND " + "Estado = " + ServicoState.PROVIDERDONE.v();
            List<Servico> servicos = Arrays.asList(ServicoDAO.listServicoByQuery(query3,"ID"));
            for (Servico s :
                    servicos) {
                InboxResponse ibr = new InboxResponse(s.getID(),s.getPrestador().getNome(),s.getPrestador().getEmail(),s.getPedido().getDescricao(),s.getPedido().getCategoria().getClasse().getNome(),
                        s.getPedido().getCategoria().getNome(),DateUtils.asString(s.getProposta().getHoraInicio(),0),DateUtils.asString(s.getProposta().getHoraInicio(),1),s.getPedido().getDuracao(),
                        s.getProposta().getPrecoProposto(),2);
                ibrs.add(ibr);
            }
        } catch (PersistentException e) {
            Log.e(TAG,"BD error");
        }

        if(ibrs.size() == 0) {
            Log.w(TAG,"No Inbox to show");
        }
        if(ibrs.size() > 0) {
            Log.i(TAG,"Inbox Loaded Succesfully");
        }
        return ibrs;
    }

    @Bean
    public static List<String> setSeen(String email, String body) {
        List<String> error = new ArrayList<>();
        List<String> success = new ArrayList<>();
        error.add("Error:");
        success.add("OK");

        int id_evento = -100;
        int tipo_evento = -100;
        try {
            JSONObject obj = new JSONObject(body);
            id_evento = obj.getInt("id");
            tipo_evento = obj.getInt("tipo");
        }catch (Exception e){
            Log.e(TAG,"Missing field in JSON");
            error.add("JSON");
            return error;
        }
        switch(tipo_evento){
            case -1: //Cancelamento Servico
                try {
                    Servico s = ServicoDAO.getServicoByORMID(id_evento);
                    s.setEstado(ServicoState.CANCELLEDSEEN.v());
                    ServicoDAO.save(s);
                    Log.i(TAG,"Notification Seen");
                    return success;
                } catch (PersistentException e) {
                    error.add("BD");
                    Log.e(TAG,"BD error");
                    return error;
                }

            case 1: //Proposta nova
                Log.w(TAG,"Invalid operation state");
                error.add("Event");
                return error;
        }

        return success;

    }

    @Bean
    public static List<String> rejectPropose(String idJSON, String email) {
        List<String> error = new ArrayList<>();
        List<String> success = new ArrayList<>();
        error.add("Error:");
        success.add("OK");

//        Needed Vars
        int id_proposta = -1;
        Proposta proposta;

//        Get propose ID
        try {

            JSONObject obj = new JSONObject(idJSON);
            id_proposta = obj.getInt("id_proposta");
        }catch (Exception e){
            error.add("JSON");
            Log.e(TAG,"Missing Fields in JSON");
            return error;
        }

        if(id_proposta == -1 ){
            Log.e(TAG,"JSON Missing fields");
            error.add("JSON");
            return error;
        }
        try {
            proposta = PropostaDAO.getPropostaByORMID(id_proposta);
            Pedido pedido = proposta.getPedido();
            Cliente cliente = pedido.getCliente();
//            Verificaçãões necessárias
            if (!cliente.getEmail().equals(email)){ //Verificação do Ownership do Pedido
                error.add("Pedido");
                Log.e(TAG,"Request Owner invalid");
                return error;
            }
            if(pedido.getEstado() > 0){ //Verficiação de +1 vencedores
                error.add("Pedido");
                Log.e(TAG,"Request Already have a Winner");
                return error;
            }
            proposta.setVencedora(PropostaState.REJECTED.v());
//            Save new states
            PropostaDAO.save(proposta);
            Log.i(TAG,"Propose Rejected Succesfully");
            return success;

        } catch (PersistentException e) {
            error.add("BD");
            Log.e(TAG,"BD error");
        }
        return error;
    }
}
