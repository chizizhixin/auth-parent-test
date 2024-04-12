package com.auth.system.mapper;

import com.auth.model.applet.applet_tags_details;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppletTagsDetailsMapper extends BaseMapper<applet_tags_details> {
    List<applet_tags_details> findTagsById(@Param("id") String id);
}
