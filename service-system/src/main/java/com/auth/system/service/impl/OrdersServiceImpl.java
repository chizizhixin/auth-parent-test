package com.auth.system.service.impl;

import com.auth.model.shop.Orders;
import com.auth.system.mapper.OrdersMapper;
import com.auth.system.service.OrdersService;
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

}
