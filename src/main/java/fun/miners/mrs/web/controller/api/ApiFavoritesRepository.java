package fun.miners.mrs.web.controller.api;

import fun.miners.mrs.model.dto.JsonResult;
import fun.miners.mrs.model.entity.Favorites;
import fun.miners.mrs.model.entity.Movie;
import fun.miners.mrs.model.entity.Score;
import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.FavoritesRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;

/**
 * 收藏夹接口控制器
 */
@RestController
@RequestMapping("/api/favorites")
@Api(value="收藏夹相关接口", tags = "收藏夹相关接口")
public class ApiFavoritesRepository {

    @Autowired
    private FavoritesRepository favoritesRepository;

    /**
     * 查询所有
     * @return
     */
    @GetMapping
    @ResponseBody
    @ApiOperation("查询所有收藏夹")
    public JsonResult list() {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", favoritesRepository.findAll());
    }

    /**
     * 查询xx用户的收藏夹
     * @return
     */
    @GetMapping("user/{uid}")
    @ResponseBody
    @ApiOperation("查询xx用户的收藏夹")
    @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "Integer")
    public JsonResult findByUid(@PathVariable Integer uid) {
        User user = new User();
        user.setId(uid);
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", favoritesRepository.findAllByUser(user));
    }

    /**
     * 取消收藏
     * @return
     */
    @GetMapping("/delete/{fid}")
    @ResponseBody
    @ApiOperation("取消收藏")
    @ApiImplicitParam(name = "fid", value = "收藏夹id", required = true, dataType = "Integer")
    public JsonResult delete(@PathVariable(value = "fid") Integer fid) {
        favoritesRepository.deleteById(fid);
        return new JsonResult(HttpStatus.OK.value(), "删除成功!");
    }

    /**
     * 查看xx人收藏了xx电影
     * @return
     */
    @GetMapping("/isFav/{uid}/{mid}")
    @ResponseBody
    @ApiOperation("查看xx人收藏了xx电影接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "mid", value = "电影id", required = true, dataType = "Integer")
    })
    public JsonResult isFav(@PathVariable Integer uid, @PathVariable Integer mid) {
        User user = new User();
        Movie movie = new Movie();
        user.setId(uid);
        movie.setId(mid);
        Favorites favorites = favoritesRepository.findByUserAndAndMovie(user, movie);
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", favorites);
    }

    /**
     * 用户收藏
     *
     * @return
     */
    @PostMapping("/addFav")
    @ResponseBody
    @ApiOperation("加入收藏夹")
    @ApiImplicitParam(name = "favorites", value = "收藏夹实体类", required = true, dataType = "Favorites")
    public JsonResult addFav(@RequestBody Favorites favorites) {
        Favorites favorites1 = favoritesRepository.save(favorites);
        return new JsonResult(HttpStatus.OK.value(), "已加入收藏夹!", favorites1);
    }
}
