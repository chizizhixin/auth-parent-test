package com.auth.system.service;

import com.auth.model.shop.Dish;
import com.auth.model.vo.DishVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
public interface DishService extends IService<Dish> {

	void updateStatus(String id, Integer status);

	IPage<Dish> selectPage(Page<Dish> dishes, DishVo dishVo);
}
