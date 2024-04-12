package com.auth.model.applet;

import com.auth.model.base.BaseEntity;
import com.auth.model.system.SysRole;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 */
@Data
@ApiModel(description = "小程序用户")
@TableName("applet_user")
public class appletUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "昵称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "手机")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "性别")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "头像地址")
    @TableField("head_url")
    private String headUrl;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "微信用户唯一标识")
    @TableField("open_id")
    private String open_id;

    @ApiModelProperty(value = "是否为微信用户(1：是 0：不是)")
    @TableField("iswechat")
    private Integer iswechat;

    @ApiModelProperty(value = "状态（1：正常 0：停用）")
    @TableField("status")
    private Integer status;

    //查询时，则不返回该字段的值    设置该字段在数据库表中不存在
    @TableField(select = false,exist = false)
    private String code; // 微信用户code 前端传来的

}
