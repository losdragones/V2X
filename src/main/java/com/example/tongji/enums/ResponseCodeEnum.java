package com.example.tongji.enums;

/**
 * 返回结果状态值枚举类
 */
public enum ResponseCodeEnum {
	SUCCESS(0,"成功"),
	FAILED(1,"失败");


	private final Integer code;
	private final String message;

	ResponseCodeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

