package com.auth.system.service.impl;

import com.auth.model.applet.appletUser;
import com.auth.model.applet.applet_detail_list;
import com.auth.model.applet.applet_type;
import com.auth.system.mapper.AppletTypeMapper;
import com.auth.system.mapper.AppletUserMapper;
import com.auth.system.service.AppletInfoService;
import com.auth.system.service.AppletUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppletInfoServiceImpl  extends ServiceImpl<AppletTypeMapper, applet_type> implements AppletInfoService {
//    @Override
//    public ArrayList<String> getInfoList(String id) {
//        QueryWrapper<applet_detail_list> wrapper = new QueryWrapper<>();
//        wrapper.eq("type_id",id);
//        return null;
//    }
}
