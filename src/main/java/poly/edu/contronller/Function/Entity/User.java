package poly.edu.contronller.Function.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @NotBlank(message = "Họ tên không đuọc để trống")
    private String fullname;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0[0-9]{9})$", message = "Số điện thoại gồm 9 chữ số")
    private String phone;

    private String address;

    @Column(nullable = false)
    private String role = "USER";

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts;
}
