package fun.miners.mrs.repository;

import fun.miners.mrs.model.entity.Favorites;
import fun.miners.mrs.model.entity.Movie;
import fun.miners.mrs.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * 收藏数据持久层
 */
@CrossOrigin
public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {

    /**
     * 通过用户查收藏夹
     *
     * @param user
     * @return
     */
    List<Favorites> findAllByUser(User user);

    /**
     * 查看xx人收藏了xx电影
     *
     * @param user
     * @param movie
     * @return
     */
    Favorites findByUserAndAndMovie(User user, Movie movie);
}
