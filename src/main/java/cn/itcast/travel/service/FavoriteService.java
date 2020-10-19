package cn.itcast.travel.service;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/19 9:56
 */
public interface FavoriteService {
    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavorite(String rid, int uid);

    void add(String rid, int uid);
}
