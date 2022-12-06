package com.auth.system.service;

import com.auth.model.shop.User;
import com.auth.model.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
public interface UserService extends IService<User> {

	void updateByStatus(String id, Integer status);

	IPage<User> selectPage(Page<User> userPage, UserVo userVo);
}
