package com.yihuobao.yhb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yihuobao.yhb.entity.po.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yihuobao.yhb.entity.dto.DishDTO;
import com.yihuobao.yhb.entity.dto.DishQueryDTO;
import com.yihuobao.yhb.entity.vo.DishVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */

public interface DishService extends IService<Dish> {

    DishDTO addDish(DishDTO dishDTO);

    boolean deleteDishById(Integer id);

    DishDTO updateDish(DishDTO dishDTO);

    DishVO getDishById(Integer id);

    List<DishVO> getDishByName(String name);

    Page<DishVO> queryDishPage(DishQueryDTO queryDTO);


}
