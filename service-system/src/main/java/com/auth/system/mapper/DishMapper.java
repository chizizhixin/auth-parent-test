package com.auth.system.mapper;

import com.auth.model.shop.Dish;
import com.auth.model.vo.DishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 菜品管理 Mapper 接口
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
public interface DishMapper extends BaseMapper<Dish> {
	IPage<Dish> selectPage(Page<Dish> dishes, @Param("vo") DishVo vo);

}
