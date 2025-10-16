package poly.edu.contronller.Admin.AccountAController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Jpa.UserDao;
import poly.edu.contronller.Function.Service.SessionService;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    UserDao dao;

    @Autowired
    SessionService sessionService;

    @GetMapping("/login")
    public String formAdmin(){
        return "Admin/Account/Login";
    }

    @PostMapping("login")
    public String save(@ModelAttribute User user, Model model){
        User admin = dao.findByEmail(user.getEmail());
        
        if (admin == null){
            model.addAttribute("message","Email không tồn tại");
        } else if (!user.getPassword().equals(user.getPassword())) {
            model.addAttribute("message","Sai mật khẩu");
        } else if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {
            model.addAttribute("message","Bạn không có quyền truy cập!");
        } else {
            sessionService.set("admin",admin);
            return "redirect:/admin/dashboard";
        }

        return "admin/account/login";

    }

    @GetMapping("/logout")
    public String logout() {
        sessionService.remove("admin");
        return "redirect:/admin/account/login";
    }
}
