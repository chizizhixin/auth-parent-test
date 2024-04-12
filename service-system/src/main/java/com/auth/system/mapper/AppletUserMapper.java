package com.auth.system.mapper;


import com.auth.model.applet.appletUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-27
 */
public interface AppletUserMapper extends BaseMapper<appletUser> {
    void updateByOpenId(@Param("open_id") String open_id);

    void closePhone(@Param("id") Integer id);
}
