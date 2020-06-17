package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.util.AuthRequest;
import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import utilizador.Cliente;
import utilizador.ClienteDAO;
import utilizador.Prestador;
import utilizador.PrestadorDAO;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/cliente")
    public ResponseEntity generateTokenCliente(@RequestBody AuthRequest authRequest) throws Exception {
        String email = authRequest.getEmail();
        String email_auth = 'C'+email;
        //String password_auth = Cliente_Perfil.decodePassword(authRequest.getPassword()); // PROD: ADD THIS

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email_auth, authRequest.getPassword()) // PROD: CHANGE TO 'password_auth'
            );
        } catch (Exception ex) {
            System.out.println("[ERROR]: Cliente Not Logged");
            throw new Exception("invalid username/password");
        }

        String token =  jwtUtil.generateToken(email,'C');
        AuthResponse ar = Cliente_Perfil.loginTokenCliente(email,token);
        System.out.println("[SUCCESS]: Cliente Logged");
        return ResponseEntity.ok().body(ar);


    }

    @PostMapping("/prestador")
    public ResponseEntity generateTokenPrestador(@RequestBody AuthRequest authRequest) throws Exception {
        String email = authRequest.getEmail();
        String email_auth = 'P'+email;
        //String password_auth = Cliente_Perfil.decodePassword(authRequest.getPassword()); // PROD: ADD THIS
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email_auth, authRequest.getPassword()) // PROD: CHANGE TO 'password_auth'
            );
        } catch (Exception ex) {
            System.out.println("[ERROR]: Prestador Not Logged");
            throw new Exception("invalid username/password");
        }

        String token =  jwtUtil.generateToken(email,'P');
        AuthResponse ar = Prestador_Perfil.loginTokenPrestador(email,token);
        System.out.println("[SUCCESS]: Prestador Logged");
        return ResponseEntity.ok().body(ar);
    }
}


/*
*  Code Backup:
*
*       @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Serve Me!!";
    }
*
        String token =  jwtUtil.generateToken(email,'C');
        String q = "Email = '" + email + "'";
        Cliente[] clientes = ClienteDAO.listClienteByQuery(q,"Email");
        Cliente c = (Cliente) clientes[0];
        String nome = c.getNome();
        AuthResponse ar = new AuthResponse(token,nome);

*
*
*  //TOD: Mudar para Beans
        String q = "Email = '" + email + "'";
        Prestador[] prestadors = PrestadorDAO.listPrestadorByQuery(q,"Email");
        Prestador p = (Prestador) prestadors[0];
        String nome = p.getNome();
        AuthResponse ar = new AuthResponse(token,nome);
* */