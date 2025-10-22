package poly.edu.contronller.Function.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.contronller.Function.Entity.OrderDetail;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrder_OrderID(Integer orderId);
}
