package com.auth.system.controller.applet;


import com.auth.model.shop.OrderDetail;
import com.auth.system.result.Result;
import com.auth.system.service.OrderDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单明细表 前端控制器
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Api(tags = "订单详情管理")
@RestController
@RequestMapping("/admin/dish/order-detail")
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;

	//根据订单id查找订单详情信息
	@ApiOperation("根据订单id查找订单详情信息")
	@GetMapping("/findAll/{id}")
	public Result findById(@PathVariable String id){
		OrderDetail byId = orderDetailService.getByDetailId(id);
		return Result.ok(byId);
	}
	//根据订单id修改订单详情信息
    @ApiOperation("根据订单id修改订单详情信息")
	@PutMapping("update")
	public Result updateById(@RequestBody OrderDetail orderDetail){
		boolean updateById = orderDetailService.updateById(orderDetail);
		if (updateById){
			return  Result.ok();
		}else {
			return  Result.fail();
		}
	}
}

