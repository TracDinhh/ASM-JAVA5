package poly.edu.contronller.Function.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.contronller.Function.Entity.Order;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {
    List<Order> findByUser_UserID(Integer userID);
}
