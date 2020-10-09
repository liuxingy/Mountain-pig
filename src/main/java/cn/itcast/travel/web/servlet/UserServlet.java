package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/9/30 16:40
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取数据
        Map<String, String[]> map = req.getParameterMap();
        String check = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            ObjectMapper ob = new ObjectMapper();
            String json = ob.writeValueAsString(info);
            // 将json写回客户端
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        // 2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 调用service完成注册
        UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        // 响应结果
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("注册失败,用户名不能重复");
        }
        // 将info对象序列化为json，并且写回客户端
        ObjectMapper ob = new ObjectMapper();
        String json = ob.writeValueAsString(info);
        // 将json写回客户端
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceImpl();
        User u = service.login(user);
        ResultInfo info = new ResultInfo();
        if (u == null) {
            // 说明用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名密码错误");
        }
        // 判断用户是否激活
        if (u != null && !"Y".equals(u.getStatus())) {
            // 用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活邮箱");
        }
        if (u != null && "Y".equals(u.getStatus())) {
            req.getSession().setAttribute("user", u);//登录成功标记
            // 登录成功
            info.setFlag(true);
        }
        // 响应数据
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), info);

    }

    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        // 响应数据
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), user);
    }

    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁session
        req.getSession().invalidate();
        // 跳转页面
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }

    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            UserService userService = new UserServiceImpl();
            boolean flag = userService.active(code);
            String msg = null;
            if (flag) {
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                msg = "激活失败，请联系管理员";
            }
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(msg);
        }
    }
}
