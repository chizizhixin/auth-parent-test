package com.auth.system.service;

import com.auth.model.shop.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单明细表 服务类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
public interface OrderDetailService extends IService<OrderDetail> {

	OrderDetail getByDetailId(String id);
}
