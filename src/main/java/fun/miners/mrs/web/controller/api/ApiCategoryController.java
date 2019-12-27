package fun.miners.mrs.web.controller.api;

import fun.miners.mrs.model.dto.JsonResult;
import fun.miners.mrs.model.entity.Category;
import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.CategoryRepository;
import fun.miners.mrs.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

/**
 * 分类接口控制器
 */
@RestController
@RequestMapping("/api/category")
@Api(value="分类相关接口", tags = "分类相关接口")
public class ApiCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    /**
     * 所有分类
     * @return
     */
    @GetMapping
    @ResponseBody
    @ApiOperation("查询所有分类")
    public JsonResult list() {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", categoryRepository.findAll());
    }

    /**
     * 保存 修改分类
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    @ApiOperation("保存分类信息接口")
    @ApiImplicitParam(name = "category", value = "分类实体类", required = true, dataType = "Category")
    public JsonResult sava(@RequestBody Category category) {
        return new JsonResult(HttpStatus.OK.value(), "保存成功!", categoryRepository.save(category));
    }

    /**
     * 删除分类
     * @return
     */
    @GetMapping("/{cid}")
    @ResponseBody
    @ApiOperation("根据id查询分类")
    @ApiImplicitParam(name = "cid", value = "分类id", required = true, dataType = "Integer")
    public JsonResult findById(@PathVariable(value = "cid") Integer cid) {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", categoryRepository.findById(cid));
    }

    /**
     * 删除分类
     * @return
     */
    @GetMapping("/delete/{cid}")
    @ResponseBody
    @ApiOperation("删除分类")
    @ApiImplicitParam(name = "cid", value = "分类id", required = true, dataType = "Integer")
    public JsonResult list(@PathVariable(value = "cid") Integer cid) {
        categoryRepository.deleteById(cid);
        return new JsonResult(HttpStatus.OK.value(), "删除成功!");
    }
}
