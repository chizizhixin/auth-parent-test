package com.auth.system.service.impl;

import com.auth.model.applet.applet_tags;
import com.auth.system.mapper.AppletTagsMapper;
import com.auth.system.service.AppletTagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AppletTagsServiceImpl extends ServiceImpl<AppletTagsMapper, applet_tags> implements AppletTagsService {
}
