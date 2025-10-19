package poly.edu.contronller.Function.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.contronller.Function.Entity.Product;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findAllByOrderByCreateAtDesc();
}
