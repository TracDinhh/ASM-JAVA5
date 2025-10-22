package poly.edu.contronller.Function.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    private String productName;
    private Double price;
    private Integer quantity;
    private String image;
    private String description;

    // đây là giúp lưu cả ngày và h luôn
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreateAt")
    private Date createAt = new Date();

    // Quan hệ nhiều sản phẩm thuộc 1 danh mục
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;

    // 1 sản phẩm có thể nằm trong nhiều đơn hàng
    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;
}
