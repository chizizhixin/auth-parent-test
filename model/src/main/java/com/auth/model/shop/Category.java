package com.auth.model.shop;

import ch.qos.logback.core.util.DatePatternToRegexUtil;
import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 分类
 */
@Data
public class Category extends BaseEntity {


//    private String id;


    //类型 1 菜品分类 2 套餐分类
    private Integer type;


    //分类名称
    private String name;


    //顺序
    private Integer sort;

    //创建时间
//    @DateTimeFormat(pattern = )
//    @TableField(value = "create_time")
//    private Date createTime;
//
//
//    //更新时间
//    @TableField(value = "update_time")
//    private Date updateTime;


    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //修改人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
//
//    @TableLogic  //逻辑删除 默认效果 0 没有删除 1 已经删除
//    @TableField("is_deleted")
//    private Integer isDeleted;

    @ApiModelProperty(value = "状态(0:禁止,1:正常)")
    @TableField("status")
    private Integer status;

}
