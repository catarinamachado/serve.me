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
import servico.Servico;
import servico.ServicoDAO;
import utilizador.*;
import org.springframework.web.bind.annotation.*;

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
                        MyProfileResponse pr = new MyProfileResponse(c.getNome(),c.getEmail(),c.getNumTelemovel(),c.getMorada(),c.getFreguesia(),c.getConcelho(),c.getDistrito());
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
                        MyProfileResponse pr = new MyProfileResponse(p.getNome(),p.getEmail(),p.getNumTelemovel(),p.getMorada(),p.getFreguesia(),p.getConcelho(),p.getDistrito());
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
                if(res == 1){
                    return ResponseEntity.ok("Perfil alterado com sucesso!");
                }
                else{
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("Couldn't update your profile.");
                    return ResponseEntity.badRequest().body(er);
                }

            }
            if(tipo == 'P'){
                int res = PrestadorDAO.updatePrestadorProf(email,name,nrTelm,morada,freg,conc,distrito);
                if(res == 1){
                    return ResponseEntity.ok("Perfil alterado com sucesso!");
                }
                else{
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("Couldn't update your profile.");
                    return ResponseEntity.badRequest().body(er);
                }
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

        //verificar que password atual é esta e atualizar para a nova
        String pw_atual = jsonObject.getString("pw_atual");
        String pw_nova = jsonObject.getString("pw_nova");

        //identify User -> Client or Provider
        String q = "Email = '" + email + "'";
        if(tipo != ' '){
            if(tipo == 'C'){
                Cliente[] clientes;
                try {
                    clientes = ClienteDAO.listClienteByQuery(q, "Email");
                    if (clientes.length > 0) {
                        Cliente c = (Cliente) clientes[0];
                        String password_old = c.getPassword();
                        //If current password not matches
                        if(!password_old.equals(pw_atual)){
                            Log.e(TAG,"Cliente current password doesn't match!");
                            ErrorResponse er = new ErrorResponse();
                            er.setLocalError("Current passoword doesn't match");
                            return (ResponseEntity) ResponseEntity.badRequest().body(er);
                        }
                        else{
                            Log.i(TAG,"Cliente current password matches");
                            //If matches, lets update
                            int res = ClienteDAO.updateClientePW(email,pw_nova);
                            if(res == 1){
                                Log.i(TAG,"Cliente password succssefully updated");
                                return ResponseEntity.ok().body("Passoword update with success");
                            }
                            else{
                                Log.e(TAG,"Cliente password update went wrong");
                                ErrorResponse er = new ErrorResponse();
                                er.setLocalError("Cliente password update went wrong");
                                return (ResponseEntity) ResponseEntity.badRequest().body(er);
                            }
                        }
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
                        String password_old = p.getPassword();
                        //If current password doesnt match
                        if(!password_old.equals(pw_atual)){
                            Log.e(TAG,"Prestador current password doesn't match!");
                            ErrorResponse er = new ErrorResponse();
                            er.setLocalError("Current passoword doesn't match");
                            return (ResponseEntity) ResponseEntity.badRequest().body(er);
                        }
                        //If matches, lets update
                        else{
                            Log.i(TAG,"Prestador current password matches");
                            int res = PrestadorDAO.updatePrestadorPW(email,pw_nova);
                            if(res == 1){
                                Log.i(TAG,"Prestador password succssefully updated");
                                return ResponseEntity.ok().body("Passoword update with success");
                            }
                            else{
                                Log.e(TAG,"Prestador password update went wrong");
                                ErrorResponse er = new ErrorResponse();
                                er.setLocalError("Prestador password update went wrong");
                                return (ResponseEntity) ResponseEntity.badRequest().body(er);
                            }
                        }
                    }
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
            }
        }
        return (ResponseEntity) ResponseEntity.badRequest();
    }


    @PutMapping
    @RequestMapping("/clienteprof")
    public ResponseEntity checkCProfileFromP(HttpServletRequest request, @RequestBody String body) {

        //Identificar a pessoa em questão
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        char tipo = ' ';
        String email = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            tipo = token.charAt(0);
            email = jwtUtil.extractEmail(token);
        }

        JSONObject jsonObject = new JSONObject(body);

        //Obter email do prestador que quer ver o perfil e do Cliente em causa
        String email_client = jsonObject.getString("email_cli");

        String q = "Email = '" + email_client + "'";

        if (tipo == 'P') {
            try {
                Cliente[] cli = ClienteDAO.listClienteByQuery(q, "Email");
                if (cli.length <= 0) {
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("Cliente not found");
                    Log.e(TAG,"Cliente profile not found");
                    return ResponseEntity.badRequest().body(er);
                } else {
                    Cliente c = cli[0];
                    int id = c.getID();
                    String query = "ClienteID = ' " + id + "'";
                    // PROD: ADD THIS
                    // Servico[] serv = ServicoDAO.listServicoByQuery(query,"ID");
                    //if(serv.length > 0 ) {
                    //Bottom lines HERE
                    // }
                    //else{
                        //Log.e(TAG,"Cant acess this profile from external ways");
                        //ErrorResponse er = new ErrorResponse();
                        //er.setLocalError("Cant acess this profile from external ways);
                        //return ResponseEntity.badrequest().body(er);
                    // }
                    Avaliacao_Cliente[] avaliacao_clientes = Avaliacao_ClienteDAO.listAvaliacao_ClienteByQuery(query, "ID");
                    TheOtherProfileResponse cli_prof = new TheOtherProfileResponse(c.getNome(), c.getEmail(), c.getNumTelemovel(), c.getMorada(), c.getFreguesia(), c.getConcelho(), c.getDistrito(),c.getClassificacao(),c.getNumServicosRealizados(),c.getNumServicosCancelados(), avaliacao_clientes);
                    Log.i(TAG,"Cliente profile sent with success");
                    return ResponseEntity.ok().body(cli_prof);
                }
            } catch (PersistentException e) {
                e.printStackTrace();
            }
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        else{
            Log.e(TAG,"Clients cant access each other profiles");
            ErrorResponse er = new ErrorResponse();
            er.setLocalError("Cliente cant check other client's profiles");
            return ResponseEntity.badRequest().body(er);

        }
    }

}
