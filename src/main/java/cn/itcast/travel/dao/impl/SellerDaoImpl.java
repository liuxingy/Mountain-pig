package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/16 10:30
 */
public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findById(int id) {
        return jdbcTemplate.queryForObject("select * from tab_seller where  sid = ?",
                new BeanPropertyRowMapper<>(Seller.class),id);    }
}
