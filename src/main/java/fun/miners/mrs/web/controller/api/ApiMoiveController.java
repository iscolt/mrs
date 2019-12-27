package fun.miners.mrs.web.controller.api;

import fun.miners.mrs.model.dto.JsonResult;
import fun.miners.mrs.model.entity.Category;
import fun.miners.mrs.model.entity.Movie;
import fun.miners.mrs.repository.MovieRepository;
import fun.miners.mrs.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 电影接口控制器
 */
@RestController
@RequestMapping("/api/movie")
@Api(value="电影相关接口", tags = "电影相关接口")
public class ApiMoiveController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping
    @ResponseBody
    @ApiOperation("查询所有电影")
    public JsonResult list() {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", movieRepository.findAll());
    }

    /**
     * 查询所有排序
     * @return
     */
    @GetMapping("/order")
    @ResponseBody
    @ApiOperation("查询所有电影排序")
    public JsonResult order() {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", movieRepository.orderByScore());
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("rand")
    @ResponseBody
    @ApiOperation("随机三条数据")
    public JsonResult randThree() {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", movieRepository.randThree());
    }

    /**
     * 保存 修改电影
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    @ApiOperation("保存电影信息接口")
    @ApiImplicitParam(name = "movie", value = "电影实体类", required = true, dataType = "Movie")
    public JsonResult sava(@RequestBody Movie movie) {
        return new JsonResult(HttpStatus.OK.value(), "保存成功!", movieRepository.save(movie));
    }

    /**
     * 根据id查电影
     * @return
     */
    @GetMapping("/{mid}")
    @ResponseBody
    @ApiOperation("根据id查电影")
    @ApiImplicitParam(name = "mid", value = "电影id", required = true, dataType = "Integer")
    public JsonResult findById(@PathVariable(value = "mid") Integer mid) {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", movieRepository.findById(mid));
    }

    /**
     * 删除电影
     * @return
     */
    @GetMapping("/delete/{mid}")
    @ResponseBody
    @ApiOperation("删除电影")
    @ApiImplicitParam(name = "mid", value = "电影id", required = true, dataType = "Integer")
    public JsonResult list(@PathVariable(value = "mid") Integer mid) {
        movieRepository.deleteById(mid);
        return new JsonResult(HttpStatus.OK.value(), "删除成功!");
    }

    /**
     * 根据分类查电影
     * @return
     */
    @GetMapping("/c/{cid}")
    @ResponseBody
    @ApiOperation("根据分类查电影")
    @ApiImplicitParam(name = "cid", value = "分类id", required = true, dataType = "Integer")
    public JsonResult findByCategory(@PathVariable(value = "cid") Integer cid) {
        Category category = new Category();
        category.setId(cid);
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", movieRepository.findAllByCategory(category));
    }
}
