package poly.edu.contronller.Site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class DetailController {
    @GetMapping("/detail")
    public String detail(){
        return  "Site/ProductDetail";
    }
}
