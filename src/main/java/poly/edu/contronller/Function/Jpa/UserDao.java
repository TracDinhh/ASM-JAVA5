package poly.edu.contronller.Function.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.contronller.Function.Entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
