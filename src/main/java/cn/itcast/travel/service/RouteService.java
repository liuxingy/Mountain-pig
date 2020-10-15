package cn.itcast.travel.service;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/12 9:24
 */

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * 线路Service
 */
public interface RouteService {
    PageBean<Route> pageQuery(int cid,int currentPage,int PageSize,String ranme);

}
