package poly.edu.contronller.User.AccountUController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Jpa.UserDao;
import poly.edu.contronller.Function.Service.SessionService;

@Controller
@RequestMapping("/account")
public class AuthController {

    @Autowired
    UserDao dao;

    @Autowired
    SessionService session;

    @GetMapping("/login")
    public String login(Model model){
        return "Site/Account/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        User users = dao.findByEmail(user.getEmail());

        if (users == null){
            model.addAttribute("message","Email không tồn tại");
            return "Site/Account/login";
        }

        if (!users.getPassword().equals(user.getPassword())){
            model.addAttribute("message","Mật khẩu không chính xác");
            return "Site/Account/login";
        }
        session.set("user", users);

        return "redirect:/home/index";
    }
    /// /////////////////////////////////////////////

    @GetMapping("/logout")
    public String logout(){
        session.remove("user");
        return "redirect:/home/index";
    }
    /// ////////////////////////////////////////////
    @GetMapping("/sign-up")
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "Site/Account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signup(@Valid @ModelAttribute("user")User user,
                         BindingResult result, Model model){
        if (result.hasErrors()){
            return "Site/Account/sign-up";
        }

        if (dao.findByEmail(user.getEmail()) != null){
            model.addAttribute("errorEmail","Email đã tồn tại");
            return "Site/Account/sign-up";
        }

        user.setRole("USER");
        dao.save(user);
        model.addAttribute("message", "Đăng ký thành công!");
        return "Site/Account/sign-up";
    }
    /// ///////////////////////////////////////////
    @GetMapping("/forgot-password")
    public String forgot(){
        return "Site/Account/forgot-password";
    }
}