package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/12 9:27
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String ranme) {
        PageBean<Route> pageBean = new PageBean<>();
        // 设置当前页码
        pageBean.setCurrentPage(currentPage);
        // 设置每页显示条数
        pageBean.setPageSize(pageSize);
        int totalCount = routeDao.findTotalCount(cid,ranme);
        pageBean.setTotalCount(totalCount);
        int start = (currentPage -1) * pageSize;
        pageBean.setList(routeDao.findByPage(cid,start,pageSize,ranme));
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
