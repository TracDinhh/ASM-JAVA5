package poly.edu.contronller.Function.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.contronller.Function.Entity.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {
}
