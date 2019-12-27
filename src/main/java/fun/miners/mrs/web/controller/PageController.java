package fun.miners.mrs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 页面控制器
 */
@Controller
public class PageController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 用户列表页
     *
     * @return
     */
    @GetMapping("admin/userList")
    public String userList(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("activeUri", "userList");
        return "admin/user/list";
    }

    /**
     * 用户添加页
     *
     * @return
     */
    @GetMapping("admin/userAdd")
    public String userAdd(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("activeUri", "userList");
        return "admin/user/add";
    }

    /**
     * 用户列表页
     *
     * @return
     */
    @GetMapping("admin/movieList")
    public String movieList(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("activeUri", "movieList");
        return "admin/movie/list";
    }

    /**
     * 用户添加页
     *
     * @return
     */
    @GetMapping("admin/movieAdd")
    public String movieAdd(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("activeUri", "movieList");
        return "admin/movie/add";
    }

    /**
     * 分类管理页
     *
     * @return
     */
    @GetMapping("admin/category")
    public String category(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("activeUri", "category");
        return "admin/category/category";
    }

    /**
     * 评分管理页
     *
     * @return
     */
    @GetMapping("admin/score")
    public String score(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("activeUri", "score");
        return "admin/score/score";
    }

    /**
     * 管理员信息页
     *
     * @return
     */
    @GetMapping("admin/profile")
    public String profile(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("activeUri", "profile");
        return "admin/profile/profile";
    }

    /**
     * 前台分类页
     *
     * @return
     */
    @GetMapping("category")
    public String c(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("url", "category");
        return "category";
    }

    /**
     * 前台排行页
     *
     * @return
     */
    @GetMapping("rank")
    public String r(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("url", "rank");
        return "rank";
    }

    /**
     * 前台首页
     *
     * @return
     */
    @GetMapping("/")
    public String index(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("url", "index");
        return "index";
    }

    /**
     * 登陆页
     *
     * @return
     */
    @GetMapping("/login")
    public String login(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("url", "user");
        return "user/login";
    }

    /**
     * 注册页
     *
     * @return
     */
    @GetMapping("/register")
    public String register(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("url", "user");
        return "user/register";
    }

    /**
     * 用户中心
     *
     * @return
     */
    @GetMapping("/profile")
    public String p(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("url", "user");
        return "user/profile";
    }

    /**
     * 电影详情页
     *
     * @return
     */
    @GetMapping("/detail")
    public String detail(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("url", "index");
        return "detail";
    }
}
