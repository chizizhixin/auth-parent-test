package com.auth.system.service.impl;

import com.auth.model.shop.Orders;
import com.auth.model.vo.OrdersVo;
import com.auth.system.mapper.OrdersMapper;
import com.auth.system.service.OrdersService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

	@Override
	public void updateStatus(String id, Integer status) {
		Orders orders = baseMapper.selectById(id);
		orders.setStatus(status);
		baseMapper.updateById(orders);
	}

	@Override
	public IPage<Orders> selectPage(Page<Orders> ordersPage, OrdersVo ordersVo) {
		return baseMapper.selectPage(ordersPage,ordersVo);
	}
}
