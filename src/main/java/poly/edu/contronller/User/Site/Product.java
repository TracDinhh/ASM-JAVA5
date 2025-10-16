package poly.edu.contronller.User.Site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class Product {
    @GetMapping("/list")
    public String list(){
        return "Site/My-product";
    }
}
