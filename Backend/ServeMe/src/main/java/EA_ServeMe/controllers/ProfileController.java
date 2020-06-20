package EA_ServeMe.controllers;


import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.ErrorResponse;
import EA_ServeMe.util.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import utilizador.Prestador;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final String TAG = "[PROFILE]";


    @GetMapping("/")
    public String profile() {
        return "Here is your profile!!";
    }

}
