package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author liuxy
 * @version 1.0
 * @description:
 * @date 2020/10/9 15:32
 */
public interface CategoryService{
    List<Category> findAll();

}
