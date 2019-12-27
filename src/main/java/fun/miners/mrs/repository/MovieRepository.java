package fun.miners.mrs.repository;

import fun.miners.mrs.model.entity.Category;
import fun.miners.mrs.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * 电影数据持久层
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


    /**
     * 随机取出3条数据
     * TODO * 后期需要修改
     * @return
     */
    @Query(value = "SELECT * FROM movie  ORDER BY  RAND() LIMIT 3", nativeQuery = true)
    List<Movie> randThree();

    /**
     * 随机取出3条数据
     *
     * @return
     */
    @Query(value = "SELECT * FROM movie  ORDER BY score desc", nativeQuery = true)
    List<Movie> orderByScore();
}
