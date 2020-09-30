package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/5/16 13:41
 */
@WebServlet("/exitServlet")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁session
        req.getSession().invalidate();
        // 跳转页面
        resp.sendRedirect(req.getContextPath() + "/login.html");



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
