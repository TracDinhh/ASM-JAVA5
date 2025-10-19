package poly.edu.contronller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/index")
    public String form(){
        return "Admin/Layout";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "Admin/Dashboard";
    }

    @GetMapping("/category")
    public String category(){
        return "Admin/Category";
    }


    @GetMapping("/order")
    public String order(){
        return "Admin/Order";
    }
}
