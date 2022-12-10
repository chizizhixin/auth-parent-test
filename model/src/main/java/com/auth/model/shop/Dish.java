package com.auth.model.shop;

import com.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品
 *
 * @author Sire
 */
@Data
public class Dish extends BaseEntity {

    //菜品名称
    private String name;


    //菜品分类id
    private Long categoryId;


    //菜品价格
    private BigDecimal price;


    //商品码
    private String code;


    //图片
    private String image;


    //描述信息
    private String description;

    @ApiModelProperty(value = "状态(0:禁止,1:正常)")
    @TableField("status")
    private Integer status;


    //顺序
    private Integer sort;

    //逻辑删除 0是 未删除， 1是 删除
    @TableLogic
    private Integer isDeleted;

    //创建人
    @TableField("create_user")
    private Long createUser;


    //修改人
    @TableField("update_user")
    private Long updateUser;

}
