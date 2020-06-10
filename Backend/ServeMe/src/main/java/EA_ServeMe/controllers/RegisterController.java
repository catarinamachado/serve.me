package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utilizador.Cliente;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/cliente")
    public ResponseEntity<AuthResponse> registerCliente (@RequestBody String info){
        // TODO: - Tratar de Receber as informações do Cliente pelo body
        // TODO: - Corrigir a autenticação
        // TODO: -Verificar encriptação

        List<String> ci  = new ArrayList<>();
        //ci = Cliente_Perfil.registerCliente()
        System.out.println("Info: " + info);
        int r=1;
        if (ci.size() == 0 ) r = 0;
        Cliente c = null;
        if(r == 1 ) {
            String email = c.getEmail();
            String email_auth = 'C' + email;
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email_auth, c.getPassword())
                );

            } catch (Exception e) {

            }
            String token =  jwtUtil.generateToken(email,'C');
            AuthResponse ar = Cliente_Perfil.loginTokenCliente(email,token);

            return ResponseEntity.ok().body(ar);
        }
        return ResponseEntity.ok().body(new AuthResponse("Consegui",info));

    }

    //TODO: - Realizar operação de Registo para Prestadores
}
