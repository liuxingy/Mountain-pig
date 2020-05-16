package cn.itcast.travel.service.impl;
import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/5/16 13:43
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        // 根据用户名查询用户对象
        User username = userDao.findByUsername(user.getUsername());
        // 判断username是否为null，如果为null，调用保存的方法，不为null，用户名存在，注册失败
        if (username !=null) {
            return false;
        }
        userDao.save(user);
        return true;
    }

}
