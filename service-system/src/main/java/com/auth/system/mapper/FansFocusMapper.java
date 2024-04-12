package com.auth.system.mapper;

import com.auth.model.applet.fans_focus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FansFocusMapper extends BaseMapper<fans_focus> {


    /**
     * 查询用户的关注用户列表
     *
     * @param userId
     * @return
     */
    List<fans_focus>  findUserFocus(@Param("userId") String userId);

    /**
     * 查询用户的粉丝列表
     * @param userId
     * @return
     */
    List<fans_focus> findUserFans(@Param("userId") String userId);
}
