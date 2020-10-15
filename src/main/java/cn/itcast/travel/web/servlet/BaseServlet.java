package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/9/30 16:40
 */

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求路径
        String uri = req.getRequestURI();
        // 获取方法名称
        String method = uri.substring(uri.lastIndexOf('/') + 1);
        try {
            Method method1 = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            method1.setAccessible(true);
            method1.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    /**
     * 直接将传入的对象序列化为JSON，并且写回客户端
     * @param o
     */
    public void writeValue(Object o,HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),o);
    }

    /**
     * 直接将传入的对象序列化为JSON，返回给调用者
     * @param o
     */
    public String writeValue(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.writeValueAsString(o);
    }

}
