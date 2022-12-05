package com.auth.system.mapper;

import com.auth.model.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-28
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	List<SysMenu> findMenuListUserId(@Param("userId") String userId);
}
