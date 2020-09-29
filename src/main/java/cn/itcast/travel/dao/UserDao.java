package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/5/16 13:43
 */
public interface UserDao {
    User findByUsername(String username);

    void save(User user);

    User findByCode(String code);

    void updateStatus(User user);
}
