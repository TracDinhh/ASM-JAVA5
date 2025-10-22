package poly.edu.contronller.Function.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.contronller.Function.Entity.Cart;

import java.util.Optional;

public interface CartDao extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser_UserID(Integer userId);

}
