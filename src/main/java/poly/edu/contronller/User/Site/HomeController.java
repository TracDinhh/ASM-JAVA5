package poly.edu.contronller.User.Site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.contronller.Function.Jpa.CategoryDao;
import poly.edu.contronller.Function.Jpa.ProductDao;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryDao categoryDao;

    @GetMapping("/index")
    public String home(Model model){
        model.addAttribute("products",productDao.findAllByOrderByCreateAtDesc());
        return "Site/Home";
    }
}
