package fun.miners.mrs.web.controller;

import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.UserRepository;
import fun.miners.mrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员控制器
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * 跳转到首页
     *
     * @param session
     * @return
     */
    @GetMapping(value = {"", "/index"})
    public String index(HttpSession session) {
        // 将 uri 放入会话
        request.getSession().setAttribute("activeUri", "admin");
        return "admin/admin_index";
    }

    /**
     * 跳转到登录页的请求
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/login")
    public String login(HttpSession session) {
        final User user = (User) session.getAttribute("user_session");
//         如果session存在, 跳转到后台首页
        if (null != user) {
            return "redirect:/admin";
        } else {
            return "admin/admin_login";
        }
    }

    /**
     * 验证登录信息
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password,
                        HttpSession session, Model model) {
        // 如果session存在, 跳转到后台首页
        if (null != (User) session.getAttribute("user_session")) {
            return "redirect:/admin";
        } else {
            // 验证用户名密码
            User user = null;
            user = userRepository.findByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
            // 判断User对象是否相等
            if (null != user) {

                if (user.getRole() == 0) {
                    session.setAttribute("user_session", user);
                    // 这里添加一个日志, 登录成功
                    model.addAttribute("message", "登录成功！");
                    return "redirect:/admin";
                }

                model.addAttribute("message", "对不起! 权限不足无法登陆！");
                return login(session);
            } else {
                //model.addAttribute(LOGIN_MSG, localeMessageUtil.getMessage("code.admin.login.failed", args));
                model.addAttribute("message", "登录失败, 用户名密码错误!");
                return login(session);
            }
        }
    }

    /**
     * 退出登录 销毁session
     *
     * @param session session
     * @return 重定向到/admin/login
     */
    @GetMapping(value = "/logOut")
    public String logOut(HttpSession session) {
        final User user = (User) session.getAttribute("user_session");
        session.removeAttribute("user_session");
        return "redirect:/admin/login";
    }
}
