package fun.miners.mrs.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 管理员控制器
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    /**
     * 跳转到登录页的请求
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/login")
    public String login(HttpSession session) {
//        final User user = (User) session.getAttribute("user_session");
        // 如果session存在, 跳转到后台首页
//        if (null != user) {
//            return "redirect:/admin";
//        } else {
            return "admin/admin_login";
//        }
    }
}
