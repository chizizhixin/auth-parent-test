package com.auth.system.service.impl;

import com.auth.model.applet.appletUser;
import com.auth.system.mapper.AppletUserMapper;
import com.auth.system.service.AppletUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.HashMap;
import java.util.Map;
@Service
public class AppletUserServiceImpl extends ServiceImpl<AppletUserMapper, appletUser> implements AppletUserService {
    @Resource
    private JavaMailSender javaMailSender;
    @Autowired
    private AppletUserMapper appletUserMapper;
    @Override
    public appletUser getUserInfoByUserName(String username) {
        QueryWrapper<appletUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        //根据用户名称查询用户基本信息
        appletUser appletUser = this.getUserInfoByUserName(username);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",appletUser.getId());
        map.put("name",appletUser.getName());
        map.put("avatar",appletUser.getHeadUrl());
        map.put("description",appletUser.getDescription());
        map.put("phone",appletUser.getPhone());
        map.put("email",appletUser.getEmail());
        map.put("gender",appletUser.getGender());
        return map;
    }


    /**
     * 通过open_id查询数据库中是否有该用户
     * @param openid
     * @return
     */
    @Override
    public appletUser getUserByOpenid(String openid) {
        QueryWrapper<appletUser> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",openid);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public boolean updateByOpenid(String openid) {
        QueryWrapper<Object> wrapper = new QueryWrapper<>();

        return false;
    }

    /**
     * 通过openId获取用户信息
     * @param openId
     * @return
     */
    @Override
    public Map<String, Object> getUserInfoByOpenId(String openId) {
        //根据用户名称查询用户基本信息
        appletUser appletUser = this.getUserByOpenid(openId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",appletUser.getId());
        map.put("name",appletUser.getName());
        map.put("avatar",appletUser.getHeadUrl());
        map.put("description",appletUser.getDescription());
        map.put("phone",appletUser.getPhone());
        map.put("email",appletUser.getEmail());
        map.put("isWechat",appletUser.getIswechat());
        map.put("gender",appletUser.getGender());
        return map;
    }
    /**
     * 邮件发生验证码
     *
     * @return
     */
    public boolean sendVerificationCode(String userMail, String verificationCode){
        //创建邮件发送对象
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件发送者地址
        message.setFrom("1714633604@qq.com");
        //设置邮件接收者地址
        try {
            InternetAddress address = new InternetAddress(userMail);
            address.validate();

        } catch (AddressException e) {
            throw new RuntimeException(e);
        }
        message.setTo(userMail);
        //设置邮件主题
        message.setSubject("验证码");
        //设置邮件内容，包含验证码消息
        message.setText("您的验证码是:" + verificationCode);
        //使用javaMailSender发送邮件信息
        javaMailSender.send(message);

        return true;
    }

    @Override
    public boolean closePhone(String id) {
        appletUser appletUser = baseMapper.selectById(id);
        appletUser.setPhone("");
        int i = baseMapper.updateById(appletUser);
        return i == 1;
    }

    @Override
    public boolean bindUserMail(String userId, String userMail) {
        appletUser appletUser = baseMapper.selectById(userId);
        appletUser.setEmail(userMail);
        int i = baseMapper.updateById(appletUser);
        return i == 1;
    }

    @Override
    public boolean closeEmail(String id) {
        appletUser appletUser = baseMapper.selectById(id);
        appletUser.setEmail(null);
        int i = baseMapper.updateById(appletUser);
        return i == 1;
    }
}
