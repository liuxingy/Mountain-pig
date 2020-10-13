package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/9 15:33
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        List<Category> list = null;
        Jedis jedis = JedisUtil.getJedis();
        // Set<String> category = jedis.zrange("category", 0, -1);
        // 查询redis的cid和cname
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        if (category == null || category.size() == 0) {
            list = categoryDao.findAll();
            // 将集合的数据存到redis的category的key中
            for (int i = 0; i < list.size(); i++) {
                jedis.zadd("category",list.get(i).getCid(),list.get(i).getCname());
            }

        } else {
            // 如果不为空，将set数据存入list当中
            list = new ArrayList<>();
            for (Tuple s : category) {
                Category category1 = new Category();
                category1.setCname(s.getElement());
                category1.setCid((int) s.getScore());
                // 需要存对象进去
                list.add(category1);
            }
        }
        return list;
    }
}
