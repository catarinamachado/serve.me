package EA_ServeMe.controllers;


import EA_ServeMe.beans.Cliente_Perfil;
import EA_ServeMe.beans.Prestador_Perfil;
import EA_ServeMe.responses.ErrorResponse;
import EA_ServeMe.responses.MyProfileResponse;
import EA_ServeMe.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import utilizador.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private static final String TAG = "[PROFILE]";

    @Autowired
    JwtUtil jwtUtil;

    @CrossOrigin
    @GetMapping("/myprofile")
    public ResponseEntity profile(@RequestHeader String Authorization) {

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        String email = jwtUtil.extractEmail(token);

        if(token.startsWith("C")){
            MyProfileResponse resp = Cliente_Perfil.checkprofile(email);
            if(resp == null){
                Log.e(TAG,"Cliente Profile not found!");
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(resp);
        }
        if(token.startsWith("P")){
            MyProfileResponse resp = Prestador_Perfil.checkprofile(email);
            if(resp == null){
                Log.e(TAG,"Prestador Profile not found!");
                return (ResponseEntity) ResponseEntity.notFound();
            }
            return ResponseEntity.ok(resp);
        }
        return  ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PutMapping
    @RequestMapping("/updateprofile")
    public ResponseEntity updateprofile(@RequestHeader String Authorization, @RequestBody String body){

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        String email = jwtUtil.extractEmail(token);

        if(token.startsWith("C")){
                MyProfileResponse pr = Cliente_Perfil.parseUpdateProfile(body);
                if(pr == null){
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("UpdateProfile");
                    er.addMsg("JSON");
                    Log.e(TAG,"JSON");
                    return ResponseEntity.badRequest().body(er);
                }
                else {
                    int res = ClienteDAO.updateClienteProf(email, pr.getNome(), pr.getNrTelm(), pr.getMorada(), pr.getFreguesia(), pr.getConcelho(), pr.getDistrito());
                    if (res == 1) {
                        return ResponseEntity.ok("OK");
                    } else {
                        ErrorResponse er = new ErrorResponse();
                        er.setLocalError("UpdateProfile");
                        er.addMsg("DataBase");
                        return ResponseEntity.badRequest().body(er);
                    }
                }
        }
        if(token.startsWith("P")){
            MyProfileResponse pr = Prestador_Perfil.parseUpdateProfile(body);
            if(pr == null){
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("UpdateProfile");er.addMsg("JSON");
                return ResponseEntity.badRequest().body(er);
            }
            int res = PrestadorDAO.updatePrestadorProf(email,pr.getNome(),pr.getNrTelm(),pr.getMorada(),pr.getFreguesia(),pr.getConcelho(),pr.getDistrito());
            if(res == 1){
                return ResponseEntity.ok("OK");
            }
            else{
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("UpdateProfile");
                er.addMsg("DataBase");
                return ResponseEntity.badRequest().body(er);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin
    @PutMapping
    @RequestMapping("/updatepw")
    public ResponseEntity updatepassword(@RequestHeader String Authorization, @RequestBody String body){

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        String email = jwtUtil.extractEmail(token);

        List<String> passwords = Cliente_Perfil.parsePasswordUpdate(body);
        if(passwords.size() == 2){
            String pw_atual = passwords.get(0);
            String pw_nova = passwords.get(1);
            //String internal_pw_atual = decodePassword(pw_atual);  //PROD: ADD THIS -- Função que faz decode da password vinda do frontend

            //PROD: ADD THIS LINE
            String new_Password = new BCryptPasswordEncoder(11).encode(pw_nova);
            //identify User -> Cliente ou Prestador
            if(token.startsWith("C")){
                ResponseEntity r = Cliente_Perfil.updatePassoword(email,pw_atual,new_Password);
                return r;
            }
            if(token.startsWith("P")){
                ResponseEntity re = Prestador_Perfil.updatePassoword(email,pw_atual,new_Password);
                return re;
            }
        }
        else{
            ErrorResponse er = new ErrorResponse();
            er.setLocalError("UpdatePassword");
            er.addMsg("JSON");
            return ResponseEntity.badRequest().body(er);
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin
    @PutMapping
    @RequestMapping("/clienteprof")
    public ResponseEntity checkCProfileFromP(@RequestHeader String Authorization, @RequestBody String body) {

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        String email = jwtUtil.extractEmail(token);

        try {
            //Obter email do prestador que quer ver o perfil e do Cliente em causa
            String email_client = Cliente_Perfil.parseEmailCliJSON(body);
            if (email_client.equals("ERROR")) {
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("ClienteProfile");
                er.addMsg("JSON");
                return ResponseEntity.badRequest().body(er);
            } else {
                //garantir que quem acede é Prestador
                if (token.startsWith("P")) {
                    ResponseEntity response = Prestador_Perfil.checkClienteProfile(email_client);
                } else {
                    Log.e(TAG, "Clients cant access each other profiles");
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("ClienteProfile");
                    er.addMsg("ExternalAccess");
                    return ResponseEntity.badRequest().body(er);
                }
            }
        }
        catch(Exception e){
                e.printStackTrace();
            }
        return  ResponseEntity.badRequest().build();
    }

    @CrossOrigin
    @PutMapping
    @RequestMapping("/prestadorprof")
    public ResponseEntity checkPProfileFromC(@RequestHeader String Authorization, @RequestBody String body) {

        /* extract Token and email (Verification is already done by filter)*/
        String token = Authorization.substring(7);
        String email = jwtUtil.extractEmail(token);

        try {
            //Obter email do prestador que quer ver o perfil e do Cliente em causa
            String email_prest = Prestador_Perfil.parseEmailPresJSON(body);
            if (email_prest.equals("ERROR")) {
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("PrestadorProfile");
                er.addMsg("JSON");
                return ResponseEntity.badRequest().body(er);
            } else {
                if (token.startsWith("C")) {
                    ResponseEntity resp = Cliente_Perfil.checkPrestadorProfile(email_prest);
                    return resp;
                } else {
                    Log.e(TAG, "Prestador cant access each other profiles");
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("PrestadorProfile");
                    er.addMsg("ExternalAcess");
                    return ResponseEntity.badRequest().body(er);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

}
