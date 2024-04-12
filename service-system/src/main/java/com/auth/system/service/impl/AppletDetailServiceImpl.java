package com.auth.system.service.impl;


import com.auth.model.applet.*;
import com.auth.system.mapper.*;
import com.auth.system.service.AppletDetailService;
import com.auth.system.service.AppletTagsService;
import com.auth.system.service.FansFocusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AppletDetailServiceImpl extends ServiceImpl<AppletDetailMapper, applet_detail_list> implements AppletDetailService {

    @Resource
    private AppletImgMapper appletImgMapper;
    @Resource
    private AppletUserMapper appletUserMapper;

    @Resource
    FansFocusMapper fansFocusMapper;

    @Resource
    FansFocusService fansFocusService;

    @Resource
    AppletTagsDetailsMapper appletTagsDetailsMapper;

    @Resource
    AppletTagsService appletTagsService;

    @Override
    public HashMap<String, Object> getInfoList(String id) {
        QueryWrapper<applet_detail_list> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id", id);
        List<applet_detail_list> list = baseMapper.selectList(wrapper);
        HashMap<String, Object> maps = new HashMap<>();
        ArrayList<Object> list1 = new ArrayList<>();

        for (applet_detail_list detail : list) {
            HashMap<String, Object> map = new HashMap<>();
            //List<applet_img> imgList = appletImgMapper.selectList(new QueryWrapper<applet_img>().eq("parent_id", detail.getId()));
            appletUser appletUser = appletUserMapper.selectOne(new QueryWrapper<appletUser>().eq("id", detail.getUserId()));
            map.put("details",detail);
            //map.put("imags",imgList);
            map.put("userInfo",appletUser);
            list1.add(map);
        }
        maps.put("info",list1);
        return maps;
    }

    @Override
    public HashMap<String, Object> getHotInfo() {
        QueryWrapper<applet_detail_list> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id", 1);
        wrapper.orderByDesc("count"); // 按count字段降序排序
        wrapper.last("LIMIT 1"); // 只取一条记录

        List<applet_detail_list> appletDetailList = baseMapper.selectList(wrapper);

        if (appletDetailList != null && !appletDetailList.isEmpty()) {
            HashMap<String, Object> maps = new HashMap<>();
            maps.put("hotDetail", appletDetailList.get(0));
            appletUser appletUser = appletUserMapper.selectOne(new QueryWrapper<appletUser>().eq("id", appletDetailList.get(0).getUserId()));
            maps.put("userInfo",appletUser);
            return maps;
        }
        return new HashMap<>(); // 如果没有数据，返回空map
    }

    @Override
    public HashMap<String, Object> getContentDetail(String id) {
        ArrayList<Object> tag_list = new ArrayList<>();
        //获取文章所属的tags列表
        List<applet_tags_details> tagsList = appletTagsDetailsMapper.findTagsById(id);
        for (applet_tags_details item: tagsList){
            applet_tags tags = appletTagsService.getById(item.getTagsId());
            tag_list.add(tags);
        }
        QueryWrapper<applet_detail_list> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        applet_detail_list list = baseMapper.selectOne(wrapper);
        appletUser userInfo = appletUserMapper.selectOne(new QueryWrapper<appletUser>().eq("id", list.getUserId()));
        List<applet_img> imgs = appletImgMapper.selectList(new QueryWrapper<applet_img>().eq("parent_id", list.getId()));
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("content",list);
        maps.put("userInfo",userInfo);
        maps.put("imgs",imgs);
        maps.put("tagList",tag_list);
        return maps;
    }

    @Override
    public boolean getUserFocus(String userId, String id) {

        List<fans_focus> focus = fansFocusMapper.findUserFocus(userId);
        for (fans_focus item: focus){
            if (Objects.equals(String.valueOf(item.getFocusUserId()), id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addFocusAndChanelFocus(String typeId, String userId, String focusUserId) {
        List<fans_focus> focus = fansFocusMapper.findUserFocus(userId);
        for (fans_focus item : focus) {
            if (Objects.equals(String.valueOf(item.getFocusUserId()), focusUserId)) {
                return false;
            }
        }

        fans_focus fansFocus = new fans_focus();
        fansFocus.setUserId(Long.valueOf(userId));
        fansFocus.setFocusUserId(Long.valueOf(focusUserId));
        fansFocus.setCreateTime(new Date());
        fansFocusService.save(fansFocus);

        return true;
    }


    @Override
    public boolean removeFocusAndChanelFocus(String typeId, String userId, String focusUserId) {
        List<fans_focus> focus = fansFocusMapper.findUserFocus(userId);
        for (fans_focus item: focus){
            if (Objects.equals(String.valueOf(item.getFocusUserId()), focusUserId)) {
                item.setIsDeletedVersion(Long.valueOf(item.getId()));
                fansFocusService.updateById(item);
                fansFocusService.removeById(item.getId());
            }
        }
        return true;
    }


}
