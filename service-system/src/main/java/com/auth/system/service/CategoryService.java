package com.auth.system.service;

import com.auth.model.shop.Category;
import com.auth.model.vo.CategoryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CategoryService extends IService<Category> {
	IPage<Category> selectPage(Page<Category> categories, CategoryVo categoryVo);

	void updateStatus(String id, Integer status);
}
