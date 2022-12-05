package com.auth.system.service;

import com.auth.model.system.SysMenu;
import com.auth.model.vo.AssginMenuVo;
import com.auth.model.vo.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-28
 */
public interface SysMenuService extends IService<SysMenu> {

	List<SysMenu> findNodes();

	void removeMenuById(String id);

	List<SysMenu> findMenuByRoleId(String roleId);

	void doAssign(AssginMenuVo assginMenuVo);

	List<RouterVo> getUserMenuList(String id);

	List<String> getUserButtonList(String id);
}
