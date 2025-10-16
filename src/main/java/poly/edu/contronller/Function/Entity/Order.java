package poly.edu.contronller.Function.Entity;


import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate = new Date();

    private Double totalAmount;
    private String status = "Chờ xử lý";

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    // Getter - Setter
}

