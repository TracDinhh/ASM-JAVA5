package poly.edu.contronller.User.Cart;

import poly.edu.contronller.Function.Entity.Cart;
import poly.edu.contronller.Function.Entity.CartItem;
import java.util.Collection;

public interface CartService {
    Cart getCartByUser(Integer userId);
    void addToCart(Integer userId, Integer productId, int qty);
    void updateQuantity(Integer itemId, int qty);
    void removeItem(Integer itemId);
    void clearCart(Integer userId);
    double getTotalAmount(Integer userId);
}
