package poly.edu.contronller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Jpa.UserDao;
import poly.edu.contronller.Function.Service.SessionService;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserAController {
    @Autowired
    UserDao userDao;

    @Autowired
    SessionService sessionService;
    @GetMapping("/index")
    public String index(Model model){
        User admin = sessionService.get("admin");

        if (admin == null){
            return "redirect:/admin/account/login";
        }
        List<User> users = userDao.findByRole("USER");
        model.addAttribute("users",users);
        return "Admin/User";
    }
}
