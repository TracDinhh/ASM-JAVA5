package poly.edu.contronller.Function.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.contronller.Function.Entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
