package poly.edu.contronller.Function.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemID;

    @ManyToOne
    @JoinColumn(name = "CartID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product product;

    private int quantity;
    private double price;

}
