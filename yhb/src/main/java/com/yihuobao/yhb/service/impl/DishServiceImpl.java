package com.yihuobao.yhb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yihuobao.yhb.entity.vo.DishVO;
import com.yihuobao.yhb.mapper.DishMapper;
import com.yihuobao.yhb.entity.po.Dish;
import com.yihuobao.yhb.service.DishService;
import com.yihuobao.yhb.entity.dto.DishDTO;
import com.yihuobao.yhb.entity.dto.DishQueryDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzg
 * @since 2025-09-06
 */


@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Override
    public DishDTO addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setCreatedAt(LocalDateTime.now());
        dish.setUpdatedAt(LocalDateTime.now());
        save(dish);

        DishDTO result = new DishDTO();
        BeanUtils.copyProperties(dish, result);
        return result;
    }

    @Override
    public boolean deleteDishById(Integer id) {
        return removeById(id);
    }

    @Override
    public DishDTO updateDish(DishDTO dishDTO) {

        Dish dish = getById(dishDTO.getId());
        if (dish == null) {
            throw new RuntimeException("未找到对应的菜品");
        }

        BeanUtils.copyProperties(dishDTO, dish);
        dish.setUpdatedAt(LocalDateTime.now());
        updateById(dish);

        DishDTO result = new DishDTO();
        BeanUtils.copyProperties(dish, result);
        return result;
    }

    // 修改查询单个菜品的方法
    @Override
    public DishVO getDishById(Integer id) {
        Dish dish = getById(id);
        if (dish == null) {
            return null;
        }

        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        // 处理日期格式化
        dishVO.setProductionDate(dish.getProductionDate());
        dishVO.setCreatedAt(dish.getCreatedAt());
        return dishVO;
    }

    @Override
    public List<DishVO> getDishByName(String name) {
        // 构造查询条件：名称模糊匹配
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name); // LIKE '%name%'

        // 执行查询
        List<Dish> dishList = baseMapper.selectList(queryWrapper);

        // 转换为VO返回
        return dishList.stream().map(dish -> {
            DishVO vo = new DishVO();
            BeanUtils.copyProperties(dish, vo);
            vo.setProductionDate(dish.getProductionDate()); // 格式化日期
            vo.setCreatedAt(dish.getCreatedAt());
            return vo;
        }).collect(Collectors.toList());
    }

    // 修改分页查询方法
    @Override
    public Page<DishVO> queryDishPage(DishQueryDTO queryDTO) {
        Page<Dish> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());

        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        if (queryDTO.getName() != null && !queryDTO.getName().isEmpty()) {
            queryWrapper.like("name", queryDTO.getName());
        }
        // 其他查询条件...

        Page<Dish> dishPage = page(page, queryWrapper);

        Page<DishVO> resultPage = new Page<>();
        BeanUtils.copyProperties(dishPage, resultPage);

        dishPage.getRecords().forEach(dish -> {
            DishVO vo = new DishVO();
            BeanUtils.copyProperties(dish, vo);
            vo.setProductionDate(dish.getProductionDate());
            vo.setCreatedAt(dish.getCreatedAt());
            resultPage.getRecords().add(vo);
        });

        return resultPage;
    }
}
