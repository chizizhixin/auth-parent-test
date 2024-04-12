package com.auth.model.applet;

import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "攻略分类")
@TableName("applet_type")
public class applet_type extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "状态（1：正常 0：停用）")
    @TableField("status")
    private Integer status;


}
