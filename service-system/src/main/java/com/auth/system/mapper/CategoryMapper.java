package com.auth.system.mapper;

import com.auth.model.shop.Category;
import com.auth.model.system.SysUser;
import com.auth.model.vo.CategoryVo;
import com.auth.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
	IPage<Category> selectPage(Page<Category> categories, @Param("vo") CategoryVo vo);
}
