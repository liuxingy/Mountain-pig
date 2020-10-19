package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/16 10:29
 */
public interface SellerDao {
    Seller findById(int id);
}
