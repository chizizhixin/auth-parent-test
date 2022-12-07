package com.auth.system.controller.dish;


import com.auth.model.shop.Dish;
import com.auth.model.system.SysUser;
import com.auth.model.vo.DishVo;
import com.auth.system.result.Result;
import com.auth.system.service.DishService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/admin/dish/type")
public class DishController {

	@Autowired
	private DishService dishService;

	//查询所有商品信息
	@ApiOperation("查询所有商品信息")
	@GetMapping("findAll")
	public Result findAll(){
		List<Dish> list = dishService.list();
		return Result.ok(list);
	}
	//根据id删除商品信息
	@ApiOperation("根据id删除商品信息")
	@GetMapping("remove/{id}")
	public Result removeById(@PathVariable String id){
		boolean removeById = dishService.removeById(id);
		if (removeById){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}
	//条件分页查询
	@ApiOperation("条件分页查询")
	@GetMapping("{page}/{limit}")
	public Result list(@PathVariable Long page,
					   @PathVariable Long limit,
					   DishVo dishVo){
		Page<Dish> dishes = new Page<>(page,limit);
		IPage<Dish> pageModel  = dishService.selectPage(dishes,dishVo);
		return  Result.ok(pageModel);

	}
	//添加商品
	@ApiOperation("添加商品")
	@PostMapping("save")
	public Result save(@RequestBody Dish dish){
		boolean save = dishService.save(dish);
		if (save){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}
	//根据id查询商品
	@ApiOperation("根据id查询商品")
	@GetMapping("findAllById/{id}")
	public Result findDishById(@PathVariable String id){
		Dish dish = dishService.getById(id);
		return Result.ok(dish);
	}
	//修改商品信息
	@ApiOperation("修改商品信息")
	@PutMapping("update")
	public Result updateById(@RequestBody Dish dish){
		boolean updateById = dishService.updateById(dish);
		if (updateById){
			return  Result.ok();
		}else {
			return Result.fail();
		}
	}
	//批量删除
	@ApiOperation("批量删除")
	@DeleteMapping("batchRemove")
	public Result batchRemove(@RequestBody List<String> ids){
		boolean b = dishService.removeByIds(ids);
		if (b){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}
	//更改商品状态
	@ApiOperation("更改商品状态")
	@GetMapping("updateStatus/{id}/{status}")
	public Result updateStatus(@PathVariable String id,
							   @PathVariable Integer status){
		dishService.updateStatus(id,status);
		return Result.ok();
	}


}

