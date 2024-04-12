package com.auth.system.controller.applet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth.model.applet.appletUser;
import com.auth.model.shop.Category;
import com.auth.model.vo.appletLoginVo;
import com.auth.system.exception.AuthException;
import com.auth.system.result.Result;
import com.auth.system.service.AppletUserService;
import com.auth.system.utils.HttpClientUtil;
import com.auth.system.utils.JwtHelper;
import com.auth.system.utils.Md5Util;
import com.auth.system.utils.RandomCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 小程序
 * 用户登录模块
 */
@Api(tags = "小程序用户管理")
@RestController
@RequestMapping("/applet/user")
public class AppletUserController {

    /**
     * 存储用户的邮箱号和邮箱验证码
     */
    private ConcurrentHashMap<String, String> verificationCodes = new ConcurrentHashMap<>();
    @Resource
    private AppletUserService appletUserService;
    @ApiOperation("登录")
    @PostMapping("login")
    public Result login(@RequestBody appletLoginVo appletLoginVo){
        appletUser appletUser = appletUserService.getUserInfoByUserName(appletLoginVo.getUsername());
        //查询用户是否存在
        if(appletUser == null){
            throw new AuthException(2001,"用户不存在");
        }
        String password = appletLoginVo.getPassword();
        String md5Password = Md5Util.md5(password);
        //判断密码是否一致
        if(!appletUser.getPassword().equals(md5Password)){
            throw new AuthException(2002,"用户密码错误");
        }
        //判断用户是否可用
        if(appletUser.getStatus().intValue() == 0){
            throw new AuthException(2003,"用户已被禁用");
        }
        //根据用户id和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(appletUser.getId(), appletUser.getUsername());
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        System.out.println(map);
        return Result.ok(map);
    }
    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public Result info(HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println("token"+ token);
        String username = JwtHelper.getUsername(token);
        Map<String,Object> map = appletUserService.getUserInfo(username);
        return Result.ok(map);
    }
    @ApiOperation("微信登录")
    @PostMapping("/wxLogin")
    public Result wxLogin(@RequestBody appletUser appletUser) {

            String code = appletUser.getCode();
            String avatarUrl = appletUser.getHeadUrl();
            String userName = appletUser.getName();
            Integer gender = Integer.valueOf(appletUser.getGender());

            // 当前微信开发者的appId
            String appId = "wx55d16223b349626f";
            String appSecret = "8bda1f97572f23fda41d703e66f4d0cf";

            // 微信登录的第三方接口
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
            System.out.println(url);
            // 调用微信登录第三方接口
            // 使用你的 HTTP 请求库发送 GET 请求并获取响应数据
            // 例如使用 HttpClient 发送请求
            String result = HttpClientUtil.getInstance().sendHttpGet(url);
            System.out.println(result);
            JSONObject jsonObject = JSON.parseObject(result);
            //获取openid
            String openid = jsonObject.get("openid").toString();
            //通过openid判断用户是否存在
            appletUser userByOpenid = appletUserService.getUserByOpenid(openid);
        if (userByOpenid == null){
                //不存在 插入数据库
                System.out.println("不存在--创建用户");
                appletUser.setOpen_id(openid);
                appletUser.setIswechat(1);
                appletUser.setCreateTime(new Date());
                appletUser.setUpdateTime(new Date());
                appletUser.setIsDeleted(0);
                appletUser.setStatus(1);
                appletUserService.save(appletUser);
            }else {
                System.out.println("存在--更新用户");
                appletUser.setIswechat(1);
                appletUser.setUpdateTime(new Date());
                appletUser.setIsDeleted(0);
                appletUserService.updateById(userByOpenid);
            }
        if(userByOpenid == null && appletUser.getStatus().equals(0)){
            return Result.fail().code(400).message("用户已被禁用，请联系客服!");
        }
        String token = JwtHelper.createToken(openid, appletUser.getName());
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("openId",openid);
        return  Result.ok(map);
        }
    @ApiOperation("微信用户获取用户信息")
    @GetMapping("wxInfo")
    public Result wxInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println("token"+ token);
        String openId = JwtHelper.getUserId(token);
        Map<String,Object> map = appletUserService.getUserInfoByOpenId(openId);
        return Result.ok(map);
        }
    @ApiOperation("邮箱验证码")
    @PostMapping("sendVerificationCode")
    public Result sendVerificationCode(@RequestBody Map<String, Object> params) {
        String userMail = (String) params.get("userMail");
        String verificationCode = RandomCode.generateverificationCode();
        boolean sendCode = appletUserService.sendVerificationCode(userMail,verificationCode);
        if (sendCode){
            verificationCodes.put(userMail, verificationCode);
            return Result.ok();

        }else {
            return Result.fail();
        }
    }
    @ApiOperation("验证邮箱")
    @PostMapping("validateEmail")
    public Result validateEmail(@RequestBody Map<String, Object> params){
        String code = (String) params.get("code");
        String userMail = (String) params.get("userMail");
        String userId = (String) params.get("userId");
        String verificationCode = verificationCodes.get(userMail); // 获取验证码
        if (verificationCode != null && verificationCode.equals(code)) {
            // 验证通过
            //将邮箱保存至数据库
            boolean isBind = appletUserService.bindUserMail(userId, userMail);
            return Result.ok();
        } else {
            // 验证失败
            return Result.fail().message("验证码错误");
        }
    }
    @ApiOperation("根据id删除已绑定的手机号")
    @GetMapping("closePhone/{id}")
    public Result closePhone(@PathVariable String id){
        boolean isClose = appletUserService.closePhone(id);
        if (isClose){
            return Result.ok();
        }else {
            return Result.fail().message("解除绑定失败");
        }
    }
    @ApiOperation("根据id删除已绑定的邮箱号")
    @GetMapping("closeEmail/{id}")
    public Result closeEmail(@PathVariable String id){
        boolean isClose = appletUserService.closeEmail(id);
        if (isClose){
            return Result.ok();
        }else {
            return Result.fail().message("解除绑定失败");
        }
    }

    @ApiOperation("修改用户信息")
    @PostMapping("editUser")
    public Result editUser(@RequestBody appletUser appletUser) {
        String name = appletUser.getName();
        String gender = appletUser.getGender();
        String description = appletUser.getDescription();
        String userId = appletUser.getId();
        com.auth.model.applet.appletUser appletUser1 = appletUserService.getById(userId);
        appletUser1.setName(name);
        appletUser1.setDescription(description);
        appletUser1.setGender(gender);
        boolean b = appletUserService.updateById(appletUser1);
        if(b){
            return  Result.ok();
        }else {
            return Result.fail();
        }
    }
}

