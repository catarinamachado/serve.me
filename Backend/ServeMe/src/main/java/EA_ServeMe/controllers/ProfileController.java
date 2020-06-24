package EA_ServeMe.controllers;


import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.util.*;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import utilizador.Cliente;
import utilizador.ClienteDAO;
import utilizador.Prestador;
import org.springframework.web.bind.annotation.*;
import utilizador.PrestadorDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private static final String TAG = "[PROFILE]";

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/myprofile")
    public ResponseEntity profile(HttpServletRequest request) {

        Log.i(TAG,"Profile Check Try");

        //Parsing head of request in order to determine who is requesting
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        char tipo=' ';
        String email = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            tipo = token.charAt(0);
            email = jwtUtil.extractEmail(token);
        }

        //identify User -> Client or Provider
        String q = "Email = '" + email + "'";
        if(tipo != ' '){
            if(tipo == 'C'){
                Cliente[] clientes;
                try {
                    clientes = ClienteDAO.listClienteByQuery(q, "Email");
                    if (clientes.length > 0) {
                        Cliente c = (Cliente) clientes[0];
                        ProfileResponse pr = new ProfileResponse(c.getNome(),c.getEmail(),c.getNumTelemovel(),c.getMorada(),c.getFreguesia(),c.getConcelho(),c.getDistrito());
                        Log.i(TAG,"Cliente profile sent");
                        return ResponseEntity.ok().body(pr);
                    }
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
        }
            if(tipo == 'P'){
                Prestador[] prestadors;
                try {
                    prestadors = PrestadorDAO.listPrestadorByQuery(q, "Email");
                    if (prestadors.length > 0) {
                        Prestador p = (Prestador) prestadors[0];
                        ProfileResponse pr = new ProfileResponse(p.getNome(),p.getEmail(),p.getNumTelemovel(),p.getMorada(),p.getFreguesia(),p.getConcelho(),p.getDistrito());
                        Log.i(TAG,"Prestador profile sent");
                        return ResponseEntity.ok().body(pr);
                    }
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
            }
        }

        return (ResponseEntity) ResponseEntity.notFound();
    }


    @PutMapping
    @RequestMapping("/updateprofile")
    public ResponseEntity updateprofile(HttpServletRequest request, @RequestBody String body){


        System.out.println("Request bateu no sitio certo");

        //Identificar a pessoa em questão
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        char tipo=' ';
        String email = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            tipo = token.charAt(0);
            email = jwtUtil.extractEmail(token);
        }

        JSONObject jsonObject = new JSONObject(body);
        String name = jsonObject.getString("nome");
        String nrTelm = jsonObject.getString("nrTelm");
        String morada = jsonObject.getString("morada");
        String freg = jsonObject.getString("freguesia");
        String conc = jsonObject.getString("concelho");
        String distrito = jsonObject.getString("distrito");


        if(tipo != ' '){
            if(tipo == 'C'){
                int res = ClienteDAO.updateClienteProf(email,name,nrTelm,morada,freg,conc,distrito);
                if(res == 1)
                    return ResponseEntity.ok("Perfil alterado com sucesso!");
            }
            if(tipo == 'P'){
                int res = PrestadorDAO.updatePrestadorProf(email,name,nrTelm,morada,freg,conc,distrito);
                if(res == 1)
                    return ResponseEntity.ok("Perfil alterado com sucesso!");
            }
        }
        return (ResponseEntity) ResponseEntity.badRequest();
    }


    @PutMapping
    @RequestMapping("/updatepw")
    public ResponseEntity updatepassword(HttpServletRequest request, @RequestBody String body){


        //Identificar a pessoa em questão
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        char tipo=' ';
        String email = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            tipo = token.charAt(0);
            email = jwtUtil.extractEmail(token);
        }

        JSONObject jsonObject = new JSONObject(body);

        //TODO:verificar que password atual é esta
        String pw_atual = jsonObject.getString("passoword_atual");

        //TODO: Tratar aqui de desincriptar as pw e verificar que são igual
        String pw_nova = jsonObject.getString("passowrd_nova");
        String pw_nova2 = jsonObject.getString("password_nova2");


        return (ResponseEntity) ResponseEntity.badRequest();
    }

}
