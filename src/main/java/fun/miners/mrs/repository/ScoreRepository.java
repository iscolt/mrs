package fun.miners.mrs.repository;

import fun.miners.mrs.model.entity.Movie;
import fun.miners.mrs.model.entity.Score;
import fun.miners.mrs.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * 评分数据持久层
 */
@CrossOrigin
public interface ScoreRepository extends JpaRepository<Score, Integer> {

    /**
     * 查看有多少人评分
     *
     * @param movie
     * @return
     */
    List<Score> findAllByMovie(Movie movie);

    /**
     * 查看xx人评论了xx电影
     *
     * @param user
     * @param movie
     * @return
     */
    Score findByUserAndAndMovie(User user, Movie movie);
}
