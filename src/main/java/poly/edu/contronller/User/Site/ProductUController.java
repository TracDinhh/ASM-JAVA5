package poly.edu.contronller.User.Site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.contronller.Function.Entity.Product;
import poly.edu.contronller.Function.Jpa.ProductDao;

@Controller
@RequestMapping("/product")
public class ProductUController {

    @Autowired
    ProductDao productDao;

    @GetMapping("/list")
    public String list(){
        return "Site/My-product";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        Product product = productDao.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "Site/ProductDetail";
    }
}
