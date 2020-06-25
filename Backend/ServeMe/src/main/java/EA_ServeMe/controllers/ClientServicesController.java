package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Cliente_Services;
import EA_ServeMe.util.ErrorResponse;
import EA_ServeMe.util.JwtUtil;
import EA_ServeMe.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.List;


@RestController
@RequestMapping("/api/client-services")
public class ClientServicesController {

    private static final String TAG = "[CLIENTESERVICES]";
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/add-request")
    public ResponseEntity AddRequest(@RequestBody String request, @RequestHeader String Authorization) {

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        if(token.startsWith("P")) return ResponseEntity.badRequest().body("Cliente AcCess Only");
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


}
