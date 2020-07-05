package EA_ServeMe.beans;

import EA_ServeMe.util.Log;
import EA_ServeMe.util.RequestResponse;
import org.orm.PersistentException;
import org.springframework.context.annotation.Bean;
import servico.Pedido;
import servico.PedidoDAO;
import servico.Servico;
import servico.ServicoDAO;

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
    public static List<Servico> getServicesDone(String email) {
        List<Servico> r = new ArrayList<>();
        int id_prestador = Prestador_Perfil.getPrestadorbyEmail(email).getID();
        int estado = ServicoState.CLIENTDONE.v();
        String query = "PrestadorID = " + id_prestador + " AND " + "Estado >= " + estado;
        List<Servico> servicos = new ArrayList<>();
        try {
            servicos = Arrays.asList(ServicoDAO.listServicoByQuery(query,"PrestadorID"));
            if (servicos.size() == 0){
                Log.w(TAG,"There's no Completed Services for this Provider");
                return r;
            }
            Log.i(TAG,"Completed Services Loaded Succesfully");
            return r;
        } catch (PersistentException e) {
            Log.e(TAG,"BD error");
            return r;
        }
    }

}
