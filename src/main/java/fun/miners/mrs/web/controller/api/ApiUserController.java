package fun.miners.mrs.web.controller.api;

import fun.miners.mrs.model.dto.JsonResult;
import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.UserRepository;
import fun.miners.mrs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

/**
 * 用户接口控制器
 */
@RestController
@RequestMapping("/api/user")
@Api(value="用户相关接口", tags = "用户相关接口")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    HttpServletRequest req;

    /**
     * 修改头像
     *
     * @param uid
     * @param file
     * @return
     */
    @PostMapping("updateIcon/{uid}")
    @ResponseBody
    @ApiOperation("用户登陆接口")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    public JsonResult updateIcon(@PathVariable Integer uid, @RequestParam("file") MultipartFile file) { //1. 接受上传的文件  @RequestParam("file") MultipartFile file
        // 查看用户信息是否被上传
        User user = userRepository.getOne(uid);
//        System.out.println(user);
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            String destFileName = req.getServletContext().getRealPath("") + "uploadIcon" + File.separator + fileName;
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            //6.输出文件名
//            System.out.println(destFile + ":" + fileName);
            // 更新用户信息 TODO 部署时需要更改
            user.setIcon("http://localhost:600/uploadIcon/" + fileName);
            User jiUser = userRepository.save(user);
            return new JsonResult(HttpStatus.OK.value(), "上传成功~", jiUser);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败, " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败, " + e.getMessage());
        }
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    @ApiOperation("用户登陆接口")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    public JsonResult login(@RequestBody User user) {

        if (user == null) {
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "登录失败, 请传值!");
        }

        User user1 = userRepository.findByUsernameAndPassword(user.getUsername(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        if (user1 == null) {
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "登录失败, 账号密码错误!");
        }

        return new JsonResult(HttpStatus.OK.value(), "登录成功!", user1);
    }

    /**
     * 注册用户
     * @param
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    @ApiOperation("用户注册接口")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    public JsonResult register(@RequestBody User user) {

        if (user.getUsername().equals("")) {
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册失败, 请输入用户名!");
        } else if (user.getPassword().equals("")) {
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册失败, 请输入密码!");
        }

        User isUser = userRepository.findByUsernameAndPassword(user.getUsername(),DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        if (isUser != null) {
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册失败, 已经存在此用户!", user);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes())); // 密码加个密
        user.setRole(1); // 把用户设置为普通用户
        user.setIcon("https://static.hdslb.com/images/akari.jpg"); // 为用户设置默认头像
        user.setNickname("电影爱好者");
        User user1 = userRepository.save(user);
        return new JsonResult(HttpStatus.OK.value(), "注册成功!", user1);
    }

    /**
     * 更新用户
     * @param
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    @ApiOperation("用户修改个人信息接口")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    public JsonResult update(@RequestBody User user) {

        if (user.getUsername().equals("")) {
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "修改失败, 请输入用户名!");
        } else if (user.getPassword().equals("")) {
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "修改失败, 请输入密码!");
        } else if (user.getNickname().equals("")) {
            user.setNickname("电影爱好者");
        } else if (user.getIcon().equals("")) {
            user.setIcon("https://static.hdslb.com/images/akari.jpg");
        } else if (user.getId() == 0) {
            return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "修改失败, 不存在此用户!", user);
        }

        User isUser = userRepository.findByUsernameAndPassword(user.getUsername(),DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        if (isUser != null) {
            if (!user.getId().equals(isUser.getId())) {
                return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "修改失败, 已经存在此用户!", user);
            }
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes())); // 密码加个密
        if (user.getId() != 1) {
            user.setRole(1);
        }else {
            user.setRole(0);
        }
        User user1 = userRepository.save(user);

        return new JsonResult(HttpStatus.OK.value(), "修改成功!", user1);
    }

    /**
     * 查询用户信息
     * @return
     */
    @GetMapping("/{uid}")
    @ResponseBody
    @ApiOperation("查询用户信息")
    @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "Integer")
    public JsonResult userinfo(@PathVariable(value = "uid") Integer uid) {
        Optional<User> user = userRepository.findById(uid);
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", user);
    }

    /**
     * 删除用户
     * @return
     */
    @GetMapping("/delete/{uid}")
    @ResponseBody
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "Integer")
    public JsonResult delete(@PathVariable(value = "uid") Integer uid) {
        userRepository.deleteById(uid);
        return new JsonResult(HttpStatus.OK.value(), "删除成功!");
    }

    /**
     * 删除用户
     * @return
     */
    @GetMapping
    @ResponseBody
    @ApiOperation("查询所有非管理员用户")
    public JsonResult list() {
        return new JsonResult(HttpStatus.OK.value(), "查询成功!", userRepository.findAllByRole(1));
    }
}
