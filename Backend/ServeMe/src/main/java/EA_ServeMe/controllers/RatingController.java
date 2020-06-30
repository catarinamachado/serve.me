package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.util.ErrorResponse;
import EA_ServeMe.util.JwtUtil;
import EA_ServeMe.util.Log;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servico.Servico;
import servico.ServicoDAO;

import java.util.List;


@RestController
@RequestMapping("/api/rating")
public class RatingController {


    private static final String TAG = "[RATING]";

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/cliente")
    public ResponseEntity rating_cliente(@RequestHeader String Authorization, @RequestBody String body) {

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        String email = jwtUtil.extractEmail(token);

        if(token.startsWith("C")){
            Log.e(TAG,"Clients can't rating other clients");
            ErrorResponse er = new ErrorResponse();
            er.setLocalError("Rating");
            er.addMsg("PrestadorAccessOnly");
            return ResponseEntity.badRequest().body(er);
        }
        else{
            List<String> request = Cliente_Perfil.parseAvaliacaofromPres(body);
            if(request != null){
                //PROD : ADD THIS BLOCK
                /*
                //Verificar que existe servico
                try {
                    Servico s = ServicoDAO.getServicoByORMID(idServico);
                    if(s != null){
                        //PROD: Verificar o estado do servico
                        //Se estado for FEITO e NUNCA AVALIADO
                        //if(s.getEstado() == 1){
                            int r = Cliente_Perfil.avaliar_cliente(email,request);
                            //Como ja foi avaliado pelo prestador
                            s.setEstado(3);
                            ServicoDAO.save(s);
                        }
                    }
                    else{
                        ErrorResponse er = new ErrorResponse();
                        er.setLocalError("Rating")
                        er.addMsg("ServiceNotFound");
                        return ResponseEntity.badrequest().body(er);
                    }
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
                 */
            }
            else{
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("Rating");
                er.addMsg("JSON");
                return ResponseEntity.badRequest().body(er);
            }
        }
        return ResponseEntity.badRequest().build();
    }




    @PostMapping("/prestador")
    public ResponseEntity rating_prestador(@RequestHeader String Authorization, @RequestBody String body) {

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        String email = jwtUtil.extractEmail(token);

        if(token.startsWith("P")){
            Log.e(TAG,"Providers can't rating other clients");
            ErrorResponse er = new ErrorResponse();
            er.setLocalError("Rating");
            er.addMsg("ClientAccessOnly");
            return ResponseEntity.badRequest().body(er);
        }
        else{
            List<String> request = Prestador_Perfil.parseAvaliacaofromCli(body);
            if(request != null){
                //PROD : ADD THIS BLOCK
                /*
                //Verificar que existe servico
                try {
                    Servico s = ServicoDAO.getServicoByORMID(idServico);
                    if(s != null){
                        //PROD: Verificar o estado do servico
                        //Se estado for FEITO e NUNCA AVALIADO
                        //if(s.getEstado() == 1){
                            int r = Cliente_Perfil.avaliar_cliente(email,request);
                            //Como ja foi avaliado pelo cliente
                            s.setEstado(2);
                            ServicoDAO.save(s);
                        }
                    }
                    else{
                        ErrorResponse er = new ErrorResponse();
                        er.setLocalError("Rating")
                        er.addMsg("ServiceNotFound");
                        return ResponseEntity.badrequest().body(er);
                    }
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
                 */
            }
            else{
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("Rating");
                er.addMsg("JSON");
                return ResponseEntity.badRequest().body(er);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
