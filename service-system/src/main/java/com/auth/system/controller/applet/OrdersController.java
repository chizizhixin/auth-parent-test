package com.auth.system.controller.applet;


import com.auth.model.shop.Orders;
import com.auth.model.vo.OrdersVo;
import com.auth.system.result.Result;
import com.auth.system.service.OrderDetailService;
import com.auth.system.service.OrdersService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/admin/dish/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderDetailService orderDetailService;


	//查看所有订单
	@ApiOperation("查看所有订单")
	@GetMapping("findAll")
	public Result findAllOrders(){
		List<Orders> list = ordersService.list();
		return Result.ok(list);
	}
	//根据id删除订单信息
	@ApiOperation("根据订单id删除订单信息")
	@DeleteMapping("remove/{id}")
	public Result removeOrders(@PathVariable String id){
		ordersService.removeOrdersById(id);
		return Result.ok();
	}
	//条件分页查询
	@ApiOperation("条件分页查询")
	@GetMapping("{page}/{limit}")
	public Result page(@PathVariable Long page,
					   @PathVariable Long limit,
					   OrdersVo ordersVo){
		Page<Orders> ordersPage = new Page<>(page, limit);
		IPage<Orders> pageModel = ordersService.selectPage(ordersPage,ordersVo);
		return Result.ok(pageModel);
	}
	//添加订单信息
	@ApiOperation("添加订单信息")
	@PostMapping("save")
	public Result save(@RequestBody Orders orders){
		boolean save = ordersService.save(orders);
		if (save){
			return Result.ok();
		}else {
			return  Result.fail();
		}
	}
	//根据id查询
	@ApiOperation("根据id查询订单信息")
	@GetMapping("findById/{id}")
	public Result findById(@PathVariable String id){
		Orders byId = ordersService.getById(id);
		return  Result.ok(byId);
	}
	//修改订单信息
	@ApiOperation("修改订单信息")
	@PutMapping("update")
	public Result update(@RequestBody Orders orders){
		boolean updateById = ordersService.updateById(orders);
		if (updateById){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}
	//修改订单状态
	//更改商品状态
	@ApiOperation("更改订单状态")
	@GetMapping("updateStatus/{id}/{status}")
	public Result updateStatus(@PathVariable String id,
							   @PathVariable Integer status){
		ordersService.updateStatus(id,status);
		return Result.ok();
	}
	//批量删除订单
	@ApiOperation("批量删除接口")
	@DeleteMapping("batchRemove")
	public Result batchRemove(@RequestBody List<String> ids){
		boolean b = ordersService.removeByIds(ids);
		if (b){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}

}

