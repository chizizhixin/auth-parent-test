package com.auth.system.controller.applet;

import com.auth.model.applet.applet_detail_list;
import com.auth.model.applet.applet_type;
import com.auth.system.result.Result;
import com.auth.system.service.AppletDetailService;
import com.auth.system.service.AppletInfoService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Api(tags = "小程序攻略信息")
@RestController
@RequestMapping("/applet/cdtr")
public class AppletInfoController {

    @Resource
    private AppletInfoService appletInfoService;
    @Resource
    private AppletDetailService appletDetailService;

    @ApiOperation("获取全部分类")
    @GetMapping("getAllType")
    public Result getAllType(){
        List<applet_type> list = appletInfoService.list();
        System.out.println(list);
        return Result.ok(list);
    }

    @ApiOperation("根据分类id获取所属攻略/详情")
    @GetMapping("getInfo/{id}")
    public Result getInfo(@PathVariable String id){
          HashMap<String, Object> list =  appletDetailService.getInfoList(id);
        return  Result.ok(list);
    }

    @ApiOperation("获取热门攻略")
    @GetMapping("getHotInfo")
    public Result getHotInfo(){
        HashMap<String, Object> hotInfo = appletDetailService.getHotInfo();
        return  Result.ok(hotInfo);
    }

    @ApiOperation("根据id获取攻略详情")
    @GetMapping("getContent/{id}")
    public Result getContent(@PathVariable String id){
        HashMap<String, Object> list =  appletDetailService.getContentDetail(id);
        return  Result.ok(list);
    }

    @ApiOperation("根据id获查看用户是否关注文章作者")
    @GetMapping("findUserFocus/{userId}/{id}")
    public Result findUserFocus(@PathVariable String userId, @PathVariable String id){
        boolean isFocus =  appletDetailService.getUserFocus(userId,id);
        String status;
        if (isFocus){
            status = "1";
            return  Result.ok(status);
        }else {
            status = "0";
            return Result.ok(status);
        }
    }

    @ApiOperation("用户添加或取消关注")
    @PostMapping("addFocusAndChanelFocus")
    public Result addFocusAndChanelFocus(@RequestBody Map<String, Object> params){
        String typeId = (String) params.get("type_id");
        String userId = (String) params.get("user_id");
        String focusUserId = (String) params.get("focus_user_id");

        if (Objects.equals(typeId, "1")){
            boolean b = appletDetailService.addFocusAndChanelFocus(typeId,userId,focusUserId);
            return  Result.ok();
        }else {
            boolean b = appletDetailService.removeFocusAndChanelFocus(typeId,userId,focusUserId);
            return  Result.ok();
        }
    }


}
