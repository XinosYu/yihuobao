package com.yihuobao.yhb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yihuobao.yhb.entity.dto.DishDTO;
import com.yihuobao.yhb.entity.dto.DishQueryDTO;
import com.yihuobao.yhb.entity.vo.DishVO;
import com.yihuobao.yhb.service.DishService;
import com.yihuobao.yhb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     */
    @PostMapping("/add")
    public Result<DishDTO> addDish(@RequestBody DishDTO dishDTO) {
        return Result.success(dishService.addDish(dishDTO));
    }

    /**
     * 根据ID删除菜品
     */
    @DeleteMapping("del/{id}")
    public Result<Boolean> deleteDish(@PathVariable Integer id) {
        return Result.success(dishService.deleteDishById(id));
    }

    /**
     * 更新菜品信息
     */
    @PutMapping
    public Result<DishDTO> updateDish(@RequestBody DishDTO dishDTO) {
        return Result.success(dishService.updateDish(dishDTO));
    }

    /**
     * 根据ID查询菜品
     */
    @GetMapping("get/{id}")
    public Result<DishVO> getDishById(@PathVariable Integer id) {
        return Result.success(dishService.getDishById(id));
    }

    /**
     * 根据商品名称查询菜品
     */
    @GetMapping("get/{name}")
    public Result<List<DishVO>> getDishByName(@PathVariable String name) {
        return Result.success(dishService.getDishByName(name));
    }

    /**
     * 分页查询菜品列表
     */
    @GetMapping("/page")
    public Result<Page<DishVO>> queryDishPage(DishQueryDTO queryDTO) {
        return Result.success(dishService.queryDishPage(queryDTO));
    }
}

