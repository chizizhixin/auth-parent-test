package com.auth.system.mapper;


import com.auth.model.system.SysUser;
import com.auth.model.vo.SysRoleQueryVo;
import com.auth.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-27
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	IPage<SysUser> selectPage(Page<SysUser> pageParam, @Param("vo") SysUserQueryVo vo);

}
