package EA_ServeMe.controllers;

import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.ErrorResponse;
import EA_ServeMe.util.JwtUtil;
import EA_ServeMe.util.Log;
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

    private static final String TAG = "[REGISTER]";
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/cliente")
    public ResponseEntity registerCliente (@RequestBody String info){
        Cliente c = new Cliente();
        try {
            c = Cliente_Perfil.parseUtilizadorJSON(info);
        } catch (Exception e) {
            Log.e(TAG,"Missing Values to Insert Client");
            return ResponseEntity.unprocessableEntity().body(new AuthResponse().error("Missing Values"));
        }
        String password = c.getPassword();
        List<String >res = Cliente_Perfil.registerCliente(c);


        int ok = (res.size()>1) ? 0 : 1;
        String d1 = "-Email-> " + c.getEmail() + " -Pass-> " + c.getPassword(); Log.d(TAG,d1); // PROD: DELETE THIS
        ok = 0; // DELETE THIS
        if(ok == 1 ) {
            String email = c.getEmail();
            String email_auth = 'C' + email;
            //String password_auth = Cliente_Perfil.decodePassword(password); // PROD: ADD THIS
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email_auth, password) // PROD: CHANGE TO 'password_auth'
                );

            } catch (Exception e) {
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("Registo Cliente");
                Log.e(TAG,"Error Logging in");
                return ResponseEntity.badRequest().body(er);
            }
            String token =  jwtUtil.generateToken(email,'C');
            AuthResponse ar = Cliente_Perfil.loginTokenCliente(email,token);
            Log.i(TAG,"Cliente Registed/Logged");
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
            Log.e(TAG,"Error: " + msg);
            return ResponseEntity.badRequest().body(er);
        }

    }

    @PostMapping("/prestador")
    public ResponseEntity registerPrestador (@RequestBody String info){
        Prestador p = new Prestador();
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
        String d1 = "-Email-> " + p.getEmail() + "\n-Pass-> " + p.getPassword(); Log.d(TAG,d1);  //PROD: DELETE THIS
        ok = 0; //PROD: DELETE THIS
        if(ok == 1 ) {
            String email = p.getEmail();
            String email_auth = 'C' + email;
            //String password_auth = Prestador_Perfil.decodePassword(password); // PROD: ADD THIS
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email_auth, password) // PROD: CHANGE TO 'password'
                );

            } catch (Exception e) {
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("Registo Prestador");
                Log.e(TAG,"Error Logging in");
                return ResponseEntity.badRequest().body(er);
            }
            String token =  jwtUtil.generateToken(email,'P');
            AuthResponse ar = Prestador_Perfil.loginTokenPrestador(email,token);
            Log.i(TAG,"Prestador Registed/Logged");
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