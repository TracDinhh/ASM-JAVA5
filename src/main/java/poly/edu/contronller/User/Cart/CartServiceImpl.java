package poly.edu.contronller.User.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.contronller.Function.Entity.Cart;
import poly.edu.contronller.Function.Entity.CartItem;
import poly.edu.contronller.Function.Entity.Product;
import poly.edu.contronller.Function.Jpa.CartDao;
import poly.edu.contronller.Function.Jpa.CartItemDao;
import poly.edu.contronller.Function.Jpa.ProductDao;
import poly.edu.contronller.Function.Jpa.UserDao;

import java.util.Collection;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Cart getCartByUser(Integer userId) {
        return cartDao.findByUser_UserID(userId)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUser(userDao.findById(userId).orElse(null));
                    return cartDao.save(c);
                }
        );
    }

    @Override
    public void addToCart(Integer userId, Integer productId, int qty) {
        Cart cart = getCartByUser(userId);
        Product product = productDao.findById(productId).orElse(null);
        if (product == null) return;

        CartItem item = cartItemDao.findByCartAndProduct(cart, product)
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    newItem.setQuantity(0);
                    newItem.setPrice(product.getPrice());
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + qty);
        cartItemDao.save(item);
    }

    @Override
    public void updateQuantity(Integer itemId, int qty) {
        CartItem item = cartItemDao.findById(itemId).orElse(null);
        if (item != null) {
            item.setQuantity(qty);
            cartItemDao.save(item);
        }
    }

    @Override
    public void removeItem(Integer itemId) {
        cartItemDao.deleteById(itemId);
    }

    @Override
    public void clearCart(Integer userId) {
        Cart cart = getCartByUser(userId);
        cartItemDao.deleteAll(cart.getItems());
    }

    @Override
    public double getTotalAmount(Integer userId) {
        Cart cart = getCartByUser(userId);
        return cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }
}
