package com.example.tongji.utils.response;


import com.example.tongji.enums.ResponseCodeEnum;

/**
 * 返回结果工具类
 */
public class ResponseResult<T> {

    private Integer code;
    private String message;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(ResponseCodeEnum.SUCCESS.getCode());
        responseResult.setMessage(ResponseCodeEnum.SUCCESS.getMessage());
        responseResult.setData(data);
        return responseResult;
    }

    public static <T> ResponseResult<T> failed(String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(ResponseCodeEnum.FAILED.getCode());
        responseResult.setMessage(message);
        return responseResult;
    }

}
