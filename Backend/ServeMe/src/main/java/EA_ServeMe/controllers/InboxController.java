package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Services;
import EA_ServeMe.beans.Prestador_Services;
import EA_ServeMe.responses.ErrorResponse;
import EA_ServeMe.responses.InboxResponse;
import EA_ServeMe.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import utilizador.Prestador;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/inbox/")
public class InboxController {

    private static final String TAG = "[INBOX]";
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public ResponseEntity GetMyInbox(@RequestHeader String Authorization) {
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        char type = token.charAt(0);
        String email = jwtUtil.extractEmail(token);
        List<InboxResponse> r = new ArrayList<>();

        switch (type){
            case 'C':
                r  = Cliente_Services.getInbox(email);
                break;
            case 'P':
                return ResponseEntity.badRequest().body("Cliente Acess Only");
        }

        if (r.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(r);
    }

    @PostMapping("/seen")
    public ResponseEntity setSeen(@RequestHeader String Authorization,@RequestBody String body){
        /* extract Token and email (Verification is already done by filter) */
        String token = Authorization.substring(7);
        char type = token.charAt(0);
        String email = jwtUtil.extractEmail(token);
        List<String> r = new ArrayList<>();

        switch (type){
            case 'C':
                r  = Cliente_Services.setSeen(email,body);
                break;
            case 'P':
                r  = Prestador_Services.setSeen(email,body);
        }

        if (r.size() > 1) {
            ErrorResponse er = new ErrorResponse();
            r.remove(0);
            er.setLocalError("see-notif");
            for (String e : r) {
                er.addMsg(e);
            }
            return ResponseEntity.badRequest().body(er);
        }

        return ResponseEntity.ok("SUCCESS");


    }


}
