package poly.edu.contronller.Function.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}
