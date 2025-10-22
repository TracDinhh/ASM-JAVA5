package poly.edu.contronller.Admin.OrderAController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.contronller.Function.Entity.Order;
import poly.edu.contronller.Function.Entity.OrderDetail;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Jpa.OrderDao;
import poly.edu.contronller.Function.Jpa.OrderDetailDao;
import poly.edu.contronller.Function.Service.SessionService;
import poly.edu.contronller.User.Cart.CartService;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class OrderControllerAdmin {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    @Autowired
    SessionService sessionService;

    @Autowired
    CartService cartService;
    @GetMapping("/index")
    public String form(Model model){
        User user = sessionService.get("admin");
        if (user == null){
            return "redirect:/admin/account/login";
        }
        model.addAttribute("orders",orderDao.findAll());
        return "Admin/Order";
    }

    @PostMapping("/updateStatus")
    public String update(@RequestParam("orderId") Integer orderId,
                         @RequestParam("status") String status){
        Order order = orderDao.findById(orderId).orElse(null);
        if (order != null){
            order.setStatus(status);
            orderDao.save(order);
        }
        return "redirect:/admin/order/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        List<OrderDetail> details = orderDetailDao.findByOrder_OrderID(id);
        model.addAttribute("details", details);
        model.addAttribute("order", orderDao.findById(id).orElse(null));
        return "Admin/OrderDetail";
    }
}
