package poly.edu.contronller.User.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.contronller.Function.Entity.Cart;
import poly.edu.contronller.Function.Entity.CartItem;
import poly.edu.contronller.Function.Entity.Order;
import poly.edu.contronller.Function.Entity.OrderDetail;
import poly.edu.contronller.Function.Jpa.CartDao;
import poly.edu.contronller.Function.Jpa.CartItemDao;
import poly.edu.contronller.Function.Jpa.OrderDao;
import poly.edu.contronller.Function.Jpa.OrderDetailDao;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public void createOrder(Integer userId) {
        // Lấy giỏ hàng của user
        Cart cart = cartDao.findByUser_UserID(userId).orElse(null);
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) return;

        // Tạo order mới
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(new Date());
        order.setStatus("Chờ xử lý");

        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        order.setTotalAmount(total);

        orderDao.save(order);

        // Tạo order detail cho từng item
        for (CartItem item : cart.getItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());
            orderDetailDao.save(detail);
        }

        // Xóa giỏ hàng sau khi đặt hàng
        cartItemDao.deleteAll(cart.getItems());
    }
}
