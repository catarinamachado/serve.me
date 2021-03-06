package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.responses.AuthResponse;
import EA_ServeMe.responses.ErrorResponse;
import EA_ServeMe.util.JwtUtil;
import EA_ServeMe.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import utilizador.Cliente;

import java.util.List;

import utilizador.Prestador;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private static final String TAG = "[REGISTER]";
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin
    @PostMapping("/cliente")
    public ResponseEntity registerCliente (@RequestBody String info){
        Cliente c = new Cliente();
        //Log.d(TAG,info);
        try {
            c = Cliente_Perfil.parseUtilizadorJSON(info);
        } catch (Exception e) {
            Log.e(TAG,"Missing Values to Insert Client");
            return ResponseEntity.unprocessableEntity().body(new AuthResponse().error("Missing Values"));
        }
        String password = c.getPassword();
        List<String >res = Cliente_Perfil.registerCliente(c);


        int ok = (res.size()>1) ? 0 : 1;
        //String d1 = "-Email-> " + c.getEmail() + " -Pass-> " + c.getPassword(); Log.d(TAG,d1); // PROD: DELETE THIS
        // ok = 0; // DELETE THIS
        if(ok == 1 ) {
            String email = c.getEmail();
            String email_auth = 'C' + email;
            String password_auth = Cliente_Perfil.decodePassword(password); // PROD: ADD THIS
            String token =  "";
            AuthResponse ar = Cliente_Perfil.loginTokenCliente(email,token);
            ar.setStatus(1);
            Log.i(TAG,"Cliente Registed");
            return ResponseEntity.ok().body(ar);
        }
        else {
            res.remove(0);
            ErrorResponse er = new ErrorResponse();
            er.setLocalError("Registo Cliente");
            String msg = "";
            for (String s:
                    res) {
                er.addMsg(s);
                msg+= s + " ";
            }
            String listString = String.join(", ", res);
            Log.e(TAG,"Error: " + msg);
            return ResponseEntity.badRequest().body(listString);
        }

    }

    @CrossOrigin
    @PostMapping("/prestador")
    public ResponseEntity registerPrestador (@RequestBody String info){
        Prestador p = new Prestador();
        //Log.d(TAG,info);

        try {
            p = Prestador_Perfil.parseUtilizadorJSON(info);
        } catch (Exception e) {
            Log.e(TAG,"Missing Values to insert Prestador");
            return ResponseEntity.unprocessableEntity().body(new AuthResponse().error("Missing Values"));
        }

        List<String> res;
        String password = p.getPassword();
        res= Prestador_Perfil.registerPrestador(p);
        int ok = (res.size() > 1) ? 0 : 1;
        //String d1 = "-Email-> " + p.getEmail() + "\n-Pass-> " + p.getPassword(); Log.d(TAG,d1);  //PROD: DELETE THIS
        // ok = 0; //PROD: DELETE THIS
        if(ok == 1 ) {
            String email = p.getEmail();
            String email_auth = 'P' + email;
            String password_auth = Prestador_Perfil.decodePassword(password); // PROD: ADD THIS
            String token =  "";
            AuthResponse ar = Prestador_Perfil.loginTokenPrestador(email,token);
            ar.setStatus(1);
            Log.i(TAG,"Prestador Registed");
            return ResponseEntity.ok().body(ar);
        }
        else {
            res.remove(0);
            ErrorResponse er = new ErrorResponse();
            er.setLocalError("Registo Prestador");
            String msg = "";
            for (String s:
                 res) {
                er.addMsg(s);
                msg+= s + " ";
            }
            Log.e(TAG,"Error: " + msg);
            return ResponseEntity.badRequest().body(er);
        }
    }
}

/* CODE BACKUP
//public ResponseEntity<AuthResponse> registerCliente (@RequestBody String info){
 */
