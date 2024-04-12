package com.auth.system.mapper;

import com.auth.model.shop.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单明细表 Mapper 接口
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Repository
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

	void deleteByOrdersId(@Param("id") String id);

	OrderDetail selectByOrderDetailId(@Param("id") String id);
}
