package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/19 9:58
 */
public interface FavoriteDao {
    Favorite findByRidAndUid(int rid, int uid);

    int findCountByRid(int rid);
}
