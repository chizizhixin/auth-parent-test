package com.auth.system.service.impl;

import com.auth.model.shop.OrderDetail;
import com.auth.system.mapper.OrderDetailMapper;
import com.auth.system.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {



	@Override
	public OrderDetail getByDetailId(String id) {
		return baseMapper.selectByOrderDetailId(id);
	}
}
