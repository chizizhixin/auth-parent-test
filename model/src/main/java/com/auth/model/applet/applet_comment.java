package com.auth.model.applet;

import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "评论表")
@TableName("applet_comment")
public class applet_comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属文章id")
    @TableField("content_id")
    private Integer contentId;

    @ApiModelProperty(value = "评论的父级id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "被回复评论id")
    @TableField("reply_id")
    private Long replyId;

    @ApiModelProperty(value = "被回复人名称")
    @TableField("reply_name")
    private String replyName;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户头像")
    @TableField("user_avatar")
    private String userAvatar;

    @ApiModelProperty(value = "用户评论内容")
    @TableField("user_content")
    private String userContent;


    @ApiModelProperty(value = "用户是否点赞")
    @TableField("is_like")
    private boolean isLike;

    @ApiModelProperty(value = "用户点赞数")
    @TableField("like_count")
    private Integer likeCount;

}
