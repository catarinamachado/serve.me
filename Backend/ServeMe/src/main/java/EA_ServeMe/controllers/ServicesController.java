package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Services;
import EA_ServeMe.beans.Prestador_Services;
import EA_ServeMe.responses.*;
import EA_ServeMe.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/services")
public class ServicesController {

    private static final String TAG = "[SERVICES]";
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity getRequests(){
        List<RequestResponse> reqs = Prestador_Services.getRequests();
        int ok = (reqs.size() == 0)? 0 : 1;
        if(ok == 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reqs);
    }

    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
    @PostMapping("/accept-propose") // Para Clientes
    public ResponseEntity acceptPropose(@RequestBody String propose, @RequestHeader String Authorization){

        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        if(token.startsWith("P")) return ResponseEntity.badRequest().body("Prestador Access Only");
        String email = jwtUtil.extractEmail(token);

        List<String> r = Cliente_Services.acceptPropose(propose,email);
        int ok = (r.size()>1) ? 0 : 1;
        if (ok == 1){
            return ResponseEntity.ok("SUCCESS");
        }
        else {
            ErrorResponse er = new ErrorResponse();
            r.remove(0);
            er.setLocalError("accept-propose");
            for (String e: r) {
                er.addMsg(e);
            }
            return ResponseEntity.badRequest().body(er);
        }
    }

    @CrossOrigin
    @PostMapping("/request-proposes") //Cliente
    public ResponseEntity getRecievedProposes(@RequestHeader String Authorization,@RequestBody String json){
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        if(token.startsWith("C")) return ResponseEntity.badRequest().body("Cliente Access Only");
        String email = jwtUtil.extractEmail(token);

        List<ProposeResponse> proposes = Cliente_Services.getRecievedProposes(email,json);

        if(proposes.size() == 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proposes);
    }

    @CrossOrigin
    @GetMapping("/proposes-done") // Prestadores
    public ResponseEntity getProposesDone(@RequestHeader String Authorization){
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        if(token.startsWith("C")) return ResponseEntity.badRequest().body("Prestador Access Only");
        String email = jwtUtil.extractEmail(token);

        List<ProposeProvider> proposes = Prestador_Services.getMyProposes(email);

        if (proposes.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(proposes);
    }

    @CrossOrigin
    @GetMapping("/my-services")
    public ResponseEntity getMyServices(@RequestHeader String Authorization){
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        char type = token.charAt(0);
        String email = jwtUtil.extractEmail(token);
        List<ServiceResponse> r = new ArrayList<>();

        switch (type){
            case 'C':
                r  = Cliente_Services.getMyServices(email);
                break;
            case 'P':
                r  = Prestador_Services.getMyServices(email);
                break;
        }

        if (r.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(r);
    }

    @CrossOrigin
    @PostMapping("/scheduled-services")
    public ResponseEntity getScheduledServices(@RequestHeader String Authorization){
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        char type = token.charAt(0);
        String email = jwtUtil.extractEmail(token);
        List<ServiceResponse> r = new ArrayList<>();

        switch (type){
            case 'C':
                r  = Cliente_Services.getScheduledServices(email);
                break;
            case 'P':
                r  = Prestador_Services.getMyServices(email);
                break;
        }

        if (r.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(r);
    }

    @CrossOrigin
    @GetMapping("/completed-services")
    public ResponseEntity getCompletedServices(@RequestHeader String Authorization){
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        char type = token.charAt(0);
        String email = jwtUtil.extractEmail(token);
        List<ServiceResponse> r = new ArrayList<>();

        switch (type){
            case 'C':
                r  = Cliente_Services.getCompletedServices(email);
                break;
            case 'P':
                r  = Prestador_Services.getCompletedServices(email);
                break;
        }

        if (r.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(r);
    }

    @CrossOrigin
    @PostMapping("/next-services")
    public ResponseEntity getNextServices(@RequestHeader String Authorization){
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        char type = token.charAt(0);
        String email = jwtUtil.extractEmail(token);
        List<ServiceResponse> r = new ArrayList<>();

        switch (type){
            case 'C':
                r  = Cliente_Services.getNextServices(email);
                break;
            case 'P':
                r  = Prestador_Services.getNextServices(email);
                break;
        }

        if (r.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(r);
    }

    @CrossOrigin
    @PostMapping("/cancel-service")
    public ResponseEntity cancelService(@RequestHeader String Authorization, @RequestBody String idJSON){
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        char type = token.charAt(0);
        String email = jwtUtil.extractEmail(token);
        List<String> r = new ArrayList<>();

        switch (type){
            case 'C':
                r  = Cliente_Services.cancelService(email,idJSON);
                break;
            case 'P':
                r  = Prestador_Services.cancelService(email,idJSON);
                break;
        }

        if (r.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok("Success");
    }

    @CrossOrigin
    @PostMapping("/reject-propose")
    public ResponseEntity rejectPropose(@RequestHeader String Authorization, @RequestBody String idJSON){
        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        if(token.startsWith("P")) return ResponseEntity.badRequest().body("Cliente Access Only");
        String email = jwtUtil.extractEmail(token);

        List<String> res = Cliente_Services.rejectPropose(idJSON,email);
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
}
