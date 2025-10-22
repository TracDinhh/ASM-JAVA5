package poly.edu.contronller.User.AccountUController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.contronller.Function.Jpa.UserDao;

@Controller
@RequestMapping("/account")
public class ForgotPassword {
    @Autowired
    UserDao dao;


}
