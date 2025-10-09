package poly.edu.contronller.Account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AuthController {
    @GetMapping("/login")
    public String login(Model model){
        return "Site/Account/login";
    }

    @GetMapping("/sign-up")
    public String signup(){
        return "Site/Account/sign-up";
    }

    @GetMapping("/forgot-password")
    public String forgot(){
        return "Site/Account/forgot-password";
    }
}
