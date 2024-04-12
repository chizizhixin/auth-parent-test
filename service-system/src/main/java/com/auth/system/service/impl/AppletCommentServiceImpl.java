package com.auth.system.service.impl;

import com.auth.model.applet.applet_comment;
import com.auth.model.applet.applet_detail_list;
import com.auth.system.mapper.AppletCommentMapper;
import com.auth.system.mapper.AppletDetailMapper;
import com.auth.system.service.AppletCommentService;
import com.auth.system.service.AppletDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppletCommentServiceImpl extends ServiceImpl<AppletCommentMapper, applet_comment> implements AppletCommentService {
    @Override
    public List<applet_comment> getAllCommentsByContentId(String id) {
        QueryWrapper<applet_comment> wrapper = new QueryWrapper<>();
        wrapper.eq("content_id",id);
        return baseMapper.selectList(wrapper);
    }
}
