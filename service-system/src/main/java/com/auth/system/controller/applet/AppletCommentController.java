package com.auth.system.controller.applet;

import com.auth.model.applet.applet_comment;
import com.auth.system.result.Result;
import com.auth.system.service.AppletCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "小程序用户评论")
@RestController
@RequestMapping("/applet/comment")
public class AppletCommentController {

    @Resource
    private AppletCommentService appletCommentService;

    /*
      根据文章id获取，该篇文章下的所有评论
     */
    @ApiOperation("根据文章id获取所有评论")
    @GetMapping("all/{id}")
    public Result getAllComments(@PathVariable String id){
        List<applet_comment> comments = appletCommentService.getAllCommentsByContentId(id);
        System.out.println(comments);
        return Result.ok(comments);
    }

    /*
      点赞接口
     */
    @ApiOperation("点赞事件")
    @PostMapping("like")
    public Result like(@RequestBody  applet_comment applet_comment){
        System.out.println("传递的数据:" + applet_comment);
        appletCommentService.updateById(applet_comment);
        return Result.ok();
    }
    /*
      添加评论
     */
    @ApiOperation("添加评论")
    @PostMapping("reply")
    public Result reply(@RequestBody  applet_comment applet_comment){
        System.out.println("传递的数据:" + applet_comment);
        appletCommentService.saveOrUpdate(applet_comment);
//        Long savedCommentId = Long.valueOf(applet_comment.getId());
//        System.out.println(applet_comment);
        return Result.ok(applet_comment);
    }

    /*
      删除二级评论
     */
    @ApiOperation("删除二级评论")
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        System.out.println(id);
        boolean b = appletCommentService.removeById(id);
        if (b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

}
