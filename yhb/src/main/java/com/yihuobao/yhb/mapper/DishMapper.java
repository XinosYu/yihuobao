package com.yihuobao.yhb.mapper;

import com.yihuobao.yhb.entity.po.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yihuobao.yhb.entity.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    List<DishVO> selectByName(String name);
}
