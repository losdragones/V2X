package com.example.tongji.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户信息VO
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends BaseVO{

	/**
	 * 用户编号
	 */
	private Integer id;

	/**
	 * 用户姓名
	 */
	private String userName;

	/**
	 * 用户姓名
	 */
	private Integer userAge;

	/**
	 * 用户职位
	 */
	private String userTitle;

}
