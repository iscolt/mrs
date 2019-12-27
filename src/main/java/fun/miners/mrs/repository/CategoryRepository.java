package fun.miners.mrs.repository;

import fun.miners.mrs.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 分类数据持久层
 */
@CrossOrigin
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
