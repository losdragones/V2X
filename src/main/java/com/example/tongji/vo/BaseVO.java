package com.example.tongji.vo;

//import com.sun.istack.internal.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页信息VO
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseVO {

	/**
	 * 当前记录起始索引
	 */
	@NotNull
	@Min(0)
	private Integer pageNo;

	/**
	 * 每页显示记录数
	 */
	@NotNull
	@Min(1)
	private Integer pageSize;

	/**
	 * 总数
	 */
	@NotNull
	@Min(1)
	private Long total;

}

