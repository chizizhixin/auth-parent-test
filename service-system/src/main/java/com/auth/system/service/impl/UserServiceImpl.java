package com.auth.system.service.impl;

import com.auth.model.shop.User;
import com.auth.model.vo.UserVo;
import com.auth.system.mapper.UserMapper;
import com.auth.system.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public void updateByStatus(String id, Integer status) {
		User user = baseMapper.selectById(id);
		user.setStatus(status);
		baseMapper.updateById(user);
	}

	@Override
	public IPage<User> selectPage(Page<User> userPage, UserVo userVo) {
		return baseMapper.selectPage(userPage,userVo);
	}
}
