package poly.edu.contronller.User.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.contronller.Function.Entity.Cart;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Service.SessionService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/view")
    public String viewCart(Model model) {
        User user = sessionService.get("user");
        if (user == null) {
            return "redirect:/account/login";
        }

        Cart cart = cartService.getCartByUser(user.getUserID());

        if(cart.getItems() == null || cart.getItems().isEmpty() ){
            model.addAttribute("message","Hiện tại giỏ hàng chưa có sản phẩm");
            return "Site/Cart/Cart";
        }

        model.addAttribute("items", cart.getItems());
        model.addAttribute("total", cartService.getTotalAmount(user.getUserID()));
        return "Site/Cart/Cart";
    }

    // Thêm sản phẩm vào giỏ
    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Integer productId) {
        User user = sessionService.get("user");
        if (user == null) {
            return "redirect:/account/login";
        }

        cartService.addToCart(user.getUserID(), productId, 1);
        return "redirect:/cart/view";
    }

    // Cập nhật số lượng sản phẩm
    @PostMapping("/update/{itemId}")
    public String updateQuantity(@PathVariable("itemId") Integer itemId,
                                 @RequestParam("qty") int qty) {
        cartService.updateQuantity(itemId, qty);
        return "redirect:/cart/view";
    }

    // Xóa 1 sản phẩm
    @GetMapping("/remove/{itemId}")
    public String removeItem(@PathVariable("itemId") Integer itemId) {
        cartService.removeItem(itemId);
        return "redirect:/cart/view";
    }

    // Xóa toàn bộ giỏ hàng
    @GetMapping("/clear")
    public String clearCart() {
        User user = sessionService.get("user");
        if (user != null) {
            cartService.clearCart(user.getUserID());
        }
        return "redirect:/cart/view";
    }
}
