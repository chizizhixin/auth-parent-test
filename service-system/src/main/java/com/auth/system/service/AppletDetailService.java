package com.auth.system.service;

import com.auth.model.applet.applet_detail_list;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

public interface AppletDetailService extends IService<applet_detail_list> {
    HashMap<String, Object> getInfoList(String id);

    HashMap<String, Object> getHotInfo();

    HashMap<String, Object> getContentDetail(String id);

    boolean getUserFocus(String userId, String id);

    boolean addFocusAndChanelFocus(String typeId,String userId,String focusUserId);

    boolean removeFocusAndChanelFocus(String typeId, String userId, String focusUserId);
}
