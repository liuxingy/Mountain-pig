package cn.itcast.travel.web.servlet;

import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/9/28 13:54
 */
public class JDBCDemo {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update tab_user set name='xin' where uid=?";
        int update = template.update(sql, 1);
        System.out.println(update);
    }
}
