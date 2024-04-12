package com.auth.model.applet;

import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "图片表")
@TableName("applet_img")
public class applet_img extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属攻略/游记")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "图片地址")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty(value = "状态（1：正常 0：停用）")
    @TableField("status")
    private Integer status;

}
