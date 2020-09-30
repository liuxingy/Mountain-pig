package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/5/16 13:42
 */
public interface UserService {
    boolean regist(User user);

    boolean active(String code);

    User login(User user);

}
