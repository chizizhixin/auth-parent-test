package com.auth.system.mapper;

import com.auth.model.shop.Category;
import com.auth.model.shop.Orders;
import com.auth.model.vo.CategoryVo;
import com.auth.model.vo.OrdersVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
public interface OrdersMapper extends BaseMapper<Orders> {

	IPage<Orders> selectPage(Page<Orders> categories, @Param("vo") OrdersVo vo);

}
