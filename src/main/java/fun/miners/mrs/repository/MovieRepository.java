package fun.miners.mrs.repository;

import fun.miners.mrs.model.entity.Category;
import fun.miners.mrs.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * 电影持久层
 */
@CrossOrigin
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    /**
     * 通过分类查
     *
     * @param category
     * @return
     */
    List<Movie> findAllByCategory(Category category);
}
