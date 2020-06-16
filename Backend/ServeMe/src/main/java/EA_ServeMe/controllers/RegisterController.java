package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Prestador_Perfil;
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
import org.json.JSONObject;
import utilizador.Prestador;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/cliente")
    public ResponseEntity<AuthResponse> registerCliente (@RequestBody String info){
        Cliente c = new Cliente();
        try {
            c = Cliente_Perfil.parseUtilizadorJSON(info);
        } catch (Exception e) {
            System.out.println("Missing Values to insert Client");
            return ResponseEntity.unprocessableEntity().body(new AuthResponse().error("Missing Values"));
        }

        int r;
        r = Cliente_Perfil.registerCliente(c);
        System.out.println("-Email-> " + c.getEmail() + "\n-Pass-> " + c.getPassword());
        r = 0; // DELETE THIS
        if(r == 1 ) {
            String email = c.getEmail();
            String email_auth = 'C' + email;
            String password = Cliente_Perfil.decodePassword(c.getPassword());
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

    @PostMapping("/prestador")
    public ResponseEntity<AuthResponse> registerPrestador (@RequestBody String info){
        Prestador p = new Prestador();
        try {
            p = Prestador_Perfil.parseUtilizadorJSON(info);
        } catch (Exception e) {
            System.out.println("Missing Values to insert Prestador");
            return ResponseEntity.unprocessableEntity().body(new AuthResponse().error("Missing Values"));
        }

        int r;
        r = Prestador_Perfil.registerPrestador(p);
        System.out.println("-Email-> " + p.getEmail() + "\n-Pass-> " + p.getPassword());
        r = 0; // DELETE THIS
        if(r == 1 ) {
            String email = p.getEmail();
            String email_auth = 'C' + email;
            String password = Prestador_Perfil.decodePassword(p.getPassword());
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email_auth, p.getPassword())
                );

            } catch (Exception e) {

            }
            String token =  jwtUtil.generateToken(email,'P');
            AuthResponse ar = Prestador_Perfil.loginTokenPrestador(email,token);

            return ResponseEntity.ok().body(ar);
        }
        return ResponseEntity.ok().body(new AuthResponse("Consegui",info));

    }
}
