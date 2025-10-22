package poly.edu.contronller.User.Site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.contronller.Function.Entity.Cart;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Jpa.OrderDao;
import poly.edu.contronller.Function.Jpa.OrderDetailDao;
import poly.edu.contronller.Function.Service.SessionService;
import poly.edu.contronller.User.Cart.CartService;
import poly.edu.contronller.User.Order.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private CartService cartService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model model){
        User user = sessionService.get("user");
        Cart cart = cartService.getCartByUser(user.getUserID());

        model.addAttribute("items",cart.getItems());
        model.addAttribute("total", cartService.getTotalAmount(user.getUserID()));
        return "Site/Cart/Checkout";
    }

    @PostMapping("/confirm")
    public String confirm(Model model){
        User user = sessionService.get("user");

        orderService.createOrder(user.getUserID());
        model.addAttribute("message", "Thanh toán thành công! Đơn hàng của bạn đang chờ xử lý.");

        return "Site/Cart/Success";
    }
}
