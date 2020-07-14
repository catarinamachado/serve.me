package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.responses.ErrorResponse;
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

    @CrossOrigin
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
                int r = Cliente_Perfil.avaliar_cliente(email,request);
                if(r == -2){
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("RATING");
                    er.addMsg("CantRating");
                }
                else{
                    if(r == -1){
                        ErrorResponse er = new ErrorResponse();
                        er.setLocalError("RATING");
                        er.addMsg("AlreadyRating");
                    }
                    else{
                        return ResponseEntity.ok().body("RATED");
                    }
                }
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

    @CrossOrigin
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
                if(request != null){
                    int r = Prestador_Perfil.avaliar_prestador(email,request);
                    if(r == -2){
                        ErrorResponse er = new ErrorResponse();
                        er.setLocalError("RATING");
                        er.addMsg("CantRating");
                    }
                    else{
                        if(r == -1){
                            ErrorResponse er = new ErrorResponse();
                            er.setLocalError("RATING");
                            er.addMsg("AlreadyRating");
                        }
                        else{
                            return ResponseEntity.ok().body("RATED");
                        }
                    }
                }
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
