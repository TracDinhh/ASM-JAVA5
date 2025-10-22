package poly.edu.contronller.Function.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.contronller.Function.Entity.Cart;
import poly.edu.contronller.Function.Entity.CartItem;
import poly.edu.contronller.Function.Entity.Product;

import java.util.Optional;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}
