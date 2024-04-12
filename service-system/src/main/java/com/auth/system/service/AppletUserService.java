package com.auth.system.service;

import com.auth.model.applet.appletUser;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface AppletUserService extends IService<appletUser> {

    appletUser getUserInfoByUserName(String username);

    Map<String, Object> getUserInfo(String username);

    appletUser getUserByOpenid(String openid);


    boolean updateByOpenid(String openid);

    Map<String, Object> getUserInfoByOpenId(String openId);

    boolean sendVerificationCode(String userMail, String verificationCode);

    boolean closePhone(String id);

    boolean bindUserMail(String userId, String userMail);

    boolean closeEmail(String id);
}
