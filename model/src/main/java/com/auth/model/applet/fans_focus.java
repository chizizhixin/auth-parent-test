package com.auth.model.applet;

import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "粉丝/关注表")
@TableName("fans_focus")
public class fans_focus extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "被关注人ID")
    @TableField("focus_user_id")
    private Long focusUserId;

    @ApiModelProperty(value = "被删除后版本号")
    @TableField("is_deleted_version")
    private Long isDeletedVersion;

}
