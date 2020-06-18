package EA_ServeMe.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/welcome")
public class BasicController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Serve Me!!";
    }


}
