package com.auth.model.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class DishVo implements Serializable {
	private static final long  serialVersionUID = 1L;

	private String keyword;

	private String createTimeBegin;

	private String createTimeEnd;

}
