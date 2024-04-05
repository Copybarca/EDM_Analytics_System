package org.edm.controller.authorization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
public class MainController {

        @GetMapping("/login")
        public String login(){
            return"login/loginPage";
        }

        @GetMapping("/info")
        public String info(){
            return "login/info";
        }
}
