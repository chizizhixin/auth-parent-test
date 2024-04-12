package com.auth.model.applet;

import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "攻略详情")
@TableName("applet_detail_list")
public class applet_detail_list  extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "攻略标题")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "攻略文章")
    @TableField("detail")
    private String detail;

    @ApiModelProperty(value = "所属类型")
    @TableField("type_id")
    private Long typeId;

    @ApiModelProperty(value = "所属用户")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "封面图")
    @TableField("c_image")
    private String cImage;

    @ApiModelProperty(value = "状态（1：正常 0：停用）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "状态(1: 已收藏 0：未收藏)")
    @TableField("is_sc")
    private Integer isSc;

    @ApiModelProperty(value = "阅读量")
    @TableField("count")
    private Integer count;
}
