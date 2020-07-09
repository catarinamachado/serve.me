package EA_ServeMe.controllers;

import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.beans.Prestador_Services;
import EA_ServeMe.responses.ErrorResponse;
import EA_ServeMe.util.JwtUtil;
import EA_ServeMe.util.Log;
import EA_ServeMe.util.MyStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servico.Servico;
import utilizador.Prestador;

import java.util.List;


@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private static final String TAG = "[PERSONAL_STATS]";
    @Autowired
    private JwtUtil jwtUtil;


    @CrossOrigin
    @GetMapping("/my-stats")
    public ResponseEntity personalStats(@RequestHeader String Authorization) {

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        if(token.startsWith("C")) return ResponseEntity.badRequest().body("Prestador Access Only");
        String email = jwtUtil.extractEmail(token);

        Prestador p = Prestador_Perfil.getPrestadorbyEmail(email);
        if(p == null) {
            Log.e(TAG, "Prestador Not Found!");
            ErrorResponse er = new ErrorResponse();
            er.setLocalError("Stats");
            er.addMsg("PrestadorNotFound");
            return ResponseEntity.badRequest().body(er);
        }

        List<Servico> done_services = Prestador_Services.getServicesDone(email);
        if(done_services.size() == 0)
            return ResponseEntity.noContent().build();
        else{
            MyStatsResponse msr = Prestador_Perfil.generateStats(done_services);
            return ResponseEntity.ok(msr);
        }

    }


}
