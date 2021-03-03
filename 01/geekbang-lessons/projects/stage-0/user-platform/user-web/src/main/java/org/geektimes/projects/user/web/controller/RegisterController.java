package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.web.mvc.controller.PageController;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/register")
public class RegisterController implements PageController {

    @GET
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        try {

            if (Optional.ofNullable(request.getParameter("regName")).orElse("").isEmpty()) {
                request.setAttribute("errStr", "你这个参数啊，有点问题，啊");
                return "err.jsp";
            }
            if (Optional.ofNullable(request.getParameter("regPasswd")).orElse("").isEmpty()) {
                request.setAttribute("errStr", "你这个参数啊，有点问题，啊");
                return "err.jsp";
            }
            if (Optional.ofNullable(request.getParameter("regPasswdConfirm")).orElse("").isEmpty()) {
                request.setAttribute("errStr", "你这个参数啊，有点问题，啊");
                return "err.jsp";
            }
            if (!Optional.ofNullable(request.getParameter("regPasswdConfirm")).orElse("").equals(Optional.ofNullable(request.getParameter("regPasswdConfirm")).orElse(""))) {
                request.setAttribute("errStr", "你这个参数啊，有点问题，啊");
                return "err.jsp";
            }

            User user = new User();
            user.setName("范德彪");
            user.setPassword(Optional.ofNullable(request.getParameter("regPasswd")).orElse(""));
            user.setEmail("");
            user.setPhoneNumber("");
            user.setHead("/static/img/h.png");
            user.setAge(18);
            user.setTitle("辽北第一狠人/水库浪子/彪哥解梦馆馆长/范德伊彪");
            user.setAutograph("依然范德彪. 做了一场梦.");

            Optional<String> username = Optional.ofNullable(request.getParameter("regName"));
            if (username.isPresent()) {
                // 判断是否全为数字
                String s = username.get();
                boolean notDig = false;
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (c == '@') {
                        user.setEmail(s);
                        break;
                    }
                    if (!Character.isDigit(c)) {
                        notDig = true;
                    }
                }
                if (user.getEmail().isEmpty()) {
                    if (notDig) {
                        request.setAttribute("errStr", "你这个邮箱手机号啊，有点问题，啊");
                        return "err.jsp";
                    }
                    user.setPhoneNumber(s);
                }
            }


            // userService
            UserService userService = (UserService) request.getServletContext().getAttribute("userService");
            boolean ok = userService.register(user);
            if (!ok) {
                request.setAttribute("errStr", "不好意思啊，出错了。");
                return "err.jsp";
            }

            request.setAttribute("head", user.getHead());
            request.setAttribute("userName", user.getName());
            request.setAttribute("age", user.getAge());
            request.setAttribute("title", user.getTitle());
            request.setAttribute("autograph", user.getAutograph());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            request.setAttribute("errStr", "不好意思啊，出错了。");
            return "err.jsp";
        }

        return "userInfo.jsp";
    }
}
