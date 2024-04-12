package com.auth.model.applet;

import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("applet_tags")
public class applet_tags extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "tagName")
    @TableField("name")
    private String name;

}
