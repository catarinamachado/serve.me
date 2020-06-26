package EA_ServeMe.beans;

import EA_ServeMe.util.DateUtils;
import EA_ServeMe.util.Log;
import categorias.Categoria;
import categorias.CategoriaDAO;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.context.annotation.Bean;
import servico.Pedido;
import servico.PedidoDAO;
import utilizador.Cliente;
import utilizador.ClienteDAO;

import java.lang.reflect.Array;
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
        String q = "Email = '" + email + "'";

        /* Ensure Cliente exists */

        Cliente c;
        try {
            c = (Cliente) ClienteDAO.listClienteByQuery(q, "Email")[0];
        } catch (PersistentException e) {
            resp.add("Cliente");
            Log.e(TAG,"Problem Getting Client");
            return resp;
        }

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

            preco = obj.getDouble("preco");

            String s_dataInicio = obj.getString("dataInicio");
            dataInicio = DateUtils.toDate(s_dataInicio);

            String s_dataFim = obj.getString("dataFim");
            dataFim = DateUtils.toDate(s_dataFim);

            dur = obj.getDouble("duracao");

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
        p.setDescricao(desc);p.setEstado(0);
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
}
