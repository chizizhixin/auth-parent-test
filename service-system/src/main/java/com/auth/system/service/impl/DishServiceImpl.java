package com.auth.system.service.impl;

import com.auth.model.shop.Dish;
import com.auth.model.vo.DishVo;
import com.auth.system.mapper.DishMapper;
import com.auth.system.service.DishService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
	@Override
	public IPage<Dish> selectPage(Page<Dish> dishes, DishVo dishVo) {
		return baseMapper.selectPage(dishes,dishVo);
	}

	@Override
	public void updateStatus(String id, Integer status) {
		Dish dish = baseMapper.selectById(id);
		dish.setStatus(status);
		baseMapper.updateById(dish);
	}
}
