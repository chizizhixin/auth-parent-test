package com.auth.system.service;

import com.auth.model.shop.Orders;
import com.auth.model.vo.OrdersVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
public interface OrdersService extends IService<Orders> {

	IPage<Orders> selectPage(Page<Orders> ordersPage, OrdersVo ordersVo);

	void updateStatus(String id, Integer status);

	void removeOrdersById(String id);
}
