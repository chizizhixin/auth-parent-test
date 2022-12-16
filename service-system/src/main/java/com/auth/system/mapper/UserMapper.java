package com.auth.system.mapper;


import com.auth.model.shop.User;
import com.auth.model.vo.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
public interface UserMapper extends BaseMapper<User> {
  IPage<User> selectPage(Page<User> userPage,@Param("vo") UserVo vo);
}
