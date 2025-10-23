package poly.edu.contronller.User.AccountUController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Jpa.UserDao;
import poly.edu.contronller.Function.Mail.MailService;

import java.util.Random;

@Controller
@RequestMapping("/account")
public class ForgotPassword {
    @Autowired
    UserDao dao;

    @Autowired
    MailService mailService;

    @GetMapping("/forogt-password")
    public String form(){
        return "Site/Account/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String save(@RequestParam("email") String email, Model model){
        User user = dao.findByEmail(email);
        if (user == null) {
            model.addAttribute("error", "Email không tồn tại trong hệ thống!");
            return "Site/Account/forgot-password";
        }

        String newpass = "TMP" + (new Random().nextInt(900000) + (100000));

        user.setPassword(newpass);
        dao.save(user);

        String subject = "Yêu cầu đặt lại mật khẩu - Website của bạn";
        String body = "Xin chào " + user.getFullname() + ",\n\n"
                + "Mật khẩu tạm của bạn là: " + newpass + "\n"
                + "Vui lòng đăng nhập và đổi lại mật khẩu ngay sau khi đăng nhập.\n\n";
        mailService.sendMail(user.getEmail(), subject, body);
        return "Site/Account/forgot-password";
    }
}
