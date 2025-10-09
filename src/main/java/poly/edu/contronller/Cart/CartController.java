package poly.edu.contronller.Cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping("/add")
    public String cart(){
        return "Site/Cart/Cart";
    }
}
