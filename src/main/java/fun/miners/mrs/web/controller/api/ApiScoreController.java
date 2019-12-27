package fun.miners.mrs.web.controller.api;

import fun.miners.mrs.model.dto.JsonResult;
import fun.miners.mrs.model.entity.Movie;
import fun.miners.mrs.model.entity.Score;
import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.ScoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评分接口控制器
 */
@RestController
@RequestMapping("/api/score")
@Api(value="评分相关接口", tags = "评分相关接口")
public class ApiScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    /**
     * 查询所有
     * @return
     */
    @GetMapping
    @ResponseBody
    @ApiOperation("查询所有评分")
    public JsonResult list() {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", scoreRepository.findAll());
    }

    /**
     * 根据电影查评分
     * @return
     */
    @GetMapping("/m/{mid}")
    @ResponseBody
    @ApiOperation("根据电影查评分")
    @ApiImplicitParam(name = "mid", value = "电影id", required = true, dataType = "Integer")
    public JsonResult count(@PathVariable(value = "mid") Integer mid) {
        Movie movie = new Movie();
        movie.setId(mid);
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", scoreRepository.findAllByMovie(movie));
    }

    /**
     * 查看这部电影有多少人评分
     * @return
     */
    @PostMapping("/movie/count")
    @ResponseBody
    @ApiOperation("查看这部电影有多少人评分接口")
    @ApiImplicitParam(name = "movie", value = "电影实体类", required = true, dataType = "Movie")
    public JsonResult count(@RequestBody Movie movie) {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", scoreRepository.findAllByMovie(movie).size());
    }

    /**
     * 查看xx人评论了xx电影
     * @return
     */
    @GetMapping("/myscore/{uid}/{mid}")
    @ResponseBody
    @ApiOperation("查看xx人评论了xx电影接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "mid", value = "电影id", required = true, dataType = "Integer")
    })
    public JsonResult myscore(@PathVariable Integer uid, @PathVariable Integer mid) {
        User user = new User();
        Movie movie = new Movie();
        user.setId(uid);
        movie.setId(mid);
        Score score = scoreRepository.findByUserAndAndMovie(user, movie);
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", score);
    }

    /**
     * 用户评分
     *
     * @return
     */
    @PostMapping("/addScore")
    @ResponseBody
    @ApiOperation("用户评分")
    @ApiImplicitParam(name = "score", value = "分数实体类", required = true, dataType = "Score")
    public JsonResult addScore(@RequestBody Score score) {
        Score score1 = scoreRepository.save(score);
        return new JsonResult(HttpStatus.OK.value(), "评分成功!", score1);
    }


}
