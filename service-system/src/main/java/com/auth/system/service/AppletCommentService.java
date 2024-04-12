package com.auth.system.service;

import com.auth.model.applet.applet_comment;
import com.auth.model.applet.applet_detail_list;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AppletCommentService extends IService<applet_comment> {
    List<applet_comment> getAllCommentsByContentId(String id);
}
