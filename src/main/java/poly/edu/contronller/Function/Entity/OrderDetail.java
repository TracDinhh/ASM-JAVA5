package poly.edu.contronller.Function.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailID;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    private Integer quantity;
    private Double price;

}

