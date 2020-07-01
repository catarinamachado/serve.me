package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Cliente_Services;
import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.beans.Prestador_Services;
import EA_ServeMe.util.ErrorResponse;
import EA_ServeMe.util.JwtUtil;
import EA_ServeMe.util.Log;
import EA_ServeMe.util.RequestResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.List;


@RestController
@RequestMapping("/api/services")
public class ServicesController {

    private static final String TAG = "[SERVICES]";
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public ResponseEntity getRequests(){
        List<RequestResponse> reqs = Prestador_Services.getRequests();
        int ok = (reqs.size() == 0)? 0 : 1;
        if(ok == 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reqs);
    }

    @GetMapping("/my-requests") // Para Clientes
    public ResponseEntity getMyRequests(@RequestHeader String Authorization){

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        if(token.startsWith("P")) return ResponseEntity.badRequest().body("Cliente Access Only");
        String email = jwtUtil.extractEmail(token);

        List<RequestResponse> reqs = Cliente_Services.getRequests(email);
        int ok = (reqs.size() == 0)? 0 : 1;
        if(ok == 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reqs);
    }








    @PostMapping("/add-request") // Para Clientes
    public ResponseEntity AddRequest(@RequestBody String request, @RequestHeader String Authorization) {

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        if(token.startsWith("P")) return ResponseEntity.badRequest().body("Cliente Access Only");
        String email = jwtUtil.extractEmail(token);

        List<String> res = Cliente_Services.addRequest(request,email);
        int ok = (res.size()>1) ? 0 : 1;
        if(ok == 1){
            return ResponseEntity.ok("SUCCESS");
        }
        else {
            ErrorResponse er = new ErrorResponse();
            res.remove(0);
            er.setLocalError("add-request");
            for (String e: res) {
                er.addMsg(e);
            }
            return ResponseEntity.badRequest().body(er);
        }

    }

    @PostMapping("/edit-request")
    public ResponseEntity editRequest(@RequestBody String request, @RequestHeader String Authorization){

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        if(token.startsWith("P")) return ResponseEntity.badRequest().body("Cliente Access Only");
        String email = jwtUtil.extractEmail(token);

        List<String> res = Cliente_Services.editRequest(request,email);
        int ok = (res.size()>1) ? 0 : 1;
        if(ok == 1){
            return ResponseEntity.ok("SUCCESS");
        }
        else {
            ErrorResponse er = new ErrorResponse();
            res.remove(0);
            er.setLocalError("add-request");
            for (String e: res) {
                er.addMsg(e);
            }
            return ResponseEntity.badRequest().body(er);
        }
    }

    @DeleteMapping("/delete-request")
    public ResponseEntity removeRequest(@RequestBody String request, @RequestHeader String Authorization){
        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        if(token.startsWith("P")) return ResponseEntity.badRequest().body("Cliente Access Only");
        String email = jwtUtil.extractEmail(token);

        List<String> res = Cliente_Services.deleteRequest(request,email);
        int ok = (res.size()>1) ? 0 : 1;
        if(ok == 1){
            return ResponseEntity.ok("SUCCESS");
        }
        else {
            ErrorResponse er = new ErrorResponse();
            res.remove(0);
            er.setLocalError("add-request");
            for (String e: res) {
                er.addMsg(e);
            }
            return ResponseEntity.badRequest().body(er);
        }
    }

    @PostMapping("/propose-request") // Para Prestadores
    public ResponseEntity proposeRequest(@RequestBody String propose, @RequestHeader String Authorization){

        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        if(token.startsWith("C")) return ResponseEntity.badRequest().body("Prestador Access Only");
        String email = jwtUtil.extractEmail(token);

        List<String> res = Prestador_Services.addPropose(propose,email);
        int ok = (res.size()>1) ? 0 : 1;
        if(ok == 1){
            return ResponseEntity.ok("SUCCESS");
        }
        else {
            ErrorResponse er = new ErrorResponse();
            res.remove(0);
            er.setLocalError("propose-request");
            for (String e: res) {
                er.addMsg(e);
            }
            return ResponseEntity.badRequest().body(er);
        }
    }

    @PostMapping

}
