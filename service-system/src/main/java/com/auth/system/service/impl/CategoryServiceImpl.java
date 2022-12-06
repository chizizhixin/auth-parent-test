package com.auth.system.service.impl;

import com.auth.model.shop.Category;
import com.auth.model.vo.CategoryVo;
import com.auth.system.mapper.CategoryMapper;
import com.auth.system.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


	@Override
	public void updateStatus(String id, Integer status) {
		Category category = baseMapper.selectById(id);
		category.setStatus(status);
		baseMapper.updateById(category);
	}

	//用户列表
	@Override
	public IPage<Category> selectPage(Page<Category> categories, CategoryVo categoryVo) {
		return baseMapper.selectPage(categories,categoryVo);
	}

}
