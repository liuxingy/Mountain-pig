package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/16 9:16
 */
public interface RouteImgDao {
    List<RouteImg> findByRid(int rid);
}
