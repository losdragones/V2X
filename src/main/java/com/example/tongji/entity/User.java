package com.example.tongji.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// 注解对应的mysql表名
@TableName("user")
public class User {

	/**
	 * 用户编号 主键注解
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户姓名 字段名相同的情况下可以省略，下划线与驼峰之间可自动转换  https://baomidou.com/pages/223848/#tablefield
	 */
	// @TableField(value = "user_name")
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
