package com.auth.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

	private static final long  serialVersionUID = 1L;

	private String keyword;

	private String createTimeBegin;

	private String createTimeEnd;

}
