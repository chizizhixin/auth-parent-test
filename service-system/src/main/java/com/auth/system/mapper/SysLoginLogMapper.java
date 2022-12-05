package com.auth.system.mapper;


import com.auth.model.system.SysLoginLog;
import com.auth.model.system.SysUser;
import com.auth.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-27
 */
@Repository
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {



}
