package com.auth.system.controller.applet;

import com.auth.model.shop.Category;
import com.auth.model.vo.CategoryVo;
import com.auth.system.result.Result;
import com.auth.system.service.CategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "分类管理接口")
@RestController
@RequestMapping("/admin/dish/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	//条件分页查询分类数据
	@ApiOperation("条件分页查询")
	@GetMapping("/{page}/{limit}")
	public Result list(@PathVariable Long page,
					   @PathVariable Long limit,
					   CategoryVo categoryVo){

		Page<Category> categories = new Page<>(page,limit);
        IPage<Category> pageModel  = categoryService.selectPage(categories,categoryVo);
		return Result.ok(pageModel);
	}
	//分类添加
	@ApiOperation("添加分类")
	@PostMapping("save")
	public Result save(@RequestBody Category category){
		 categoryService.save(category);
		return Result.ok();
	}
	//根据id查询分类
	@ApiOperation("根据id查询")
	@GetMapping("getCategory/{id}")
	public Result getCategory(@PathVariable String id){
		Category category = categoryService.getById(id);
		return Result.ok(category);
	}
	//修改分类
	@ApiOperation("修改分类")
	@PutMapping("update")
	public Result update(@RequestBody Category category){
		boolean updateById = categoryService.updateById(category);
		if (updateById){
			return Result.ok();
		}else{
			return Result.fail();
		}
	}
	//删除分类
	@ApiOperation("删除分类")
	@DeleteMapping("remove/{id}")
	public Result remove(@PathVariable String id){
		boolean removeById = categoryService.removeById(id);
		if (removeById){
			return  Result.ok();
		}else {
			return Result.fail();
		}
	}

	//更改分类状态
	@ApiOperation("更改分类状态")
	@GetMapping("updateStatus/{id}/{status}")
	public Result updateStatus(@PathVariable String id,
							   @PathVariable Integer status){
		categoryService.updateStatus(id,status);
		return Result.ok();
	}
	//批量删除
	@ApiOperation("批量删除")
	@DeleteMapping("batchRemove")
	public Result batchRemove(@RequestBody List<String> ids){
		boolean b = categoryService.removeByIds(ids);
		if (b){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}

}
