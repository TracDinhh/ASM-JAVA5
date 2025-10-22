package poly.edu.contronller.User.Site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.contronller.Function.Entity.Order;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Jpa.OrderDao;
import poly.edu.contronller.Function.Service.SessionService;

@Controller
@RequestMapping("/user/order")
public class MyOrderController {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/index")
    public String index(Model model){
        User user = sessionService.get("user");
        model.addAttribute("orders", orderDao.findByUser_UserID(user.getUserID()));
        return "Site/Order/MyOrders";
    }


}
