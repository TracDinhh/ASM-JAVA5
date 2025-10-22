package poly.edu.contronller.User.AccountUController;

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
@RequestMapping("/account")
public class ProfileController {
    @Autowired
    UserDao dao;

    @Autowired
    SessionService sessionService;

    @GetMapping("/profile")
    public String profile(Model model){
        User user = sessionService.get("user");
        User dbuser = dao.findById(user.getUserID()).orElse(null);
        model.addAttribute("user",dbuser);
        return "Site/Account/edit-profile";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User update, Model model){
        User ssuser = sessionService.get("user");
        User user = dao.findById(ssuser.getUserID()).orElse(null);

        if (user != null){
            user.setFullname(update.getFullname());
            user.setEmail(update.getEmail());
            user.setPhone(update.getPhone());
            user.setAddress(update.getAddress());
            dao.save(user);

            sessionService.set("user",user);
            model.addAttribute("message","Cập nhật thông tin thành công");
        }
        model.addAttribute("user", user);
        return "Site/Account/edit-profile";
    }
}
