package com.auth.system.controller.applet;


import com.auth.system.result.Result;
import com.auth.system.service.AppletDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "小程序标签")
@RestController
@RequestMapping("/applet/tags")
public class AppletTagsController {


    @Resource
    private AppletDetailService appletDetailService;

    @ApiOperation("根据文章id查询tags")
    @GetMapping("findTags/{id}")
    public Result findTags(@PathVariable String id){



        return Result.ok();
    }

}
