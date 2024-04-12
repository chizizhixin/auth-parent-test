package com.auth.model.applet;

import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("applet_tags_details")
public class applet_tags_details extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "tags_id")
    @TableField("tags_id")
    private Long tagsId;

    @ApiModelProperty(value = "details_id")
    @TableField("details_id")
    private Long detailsId;



}
