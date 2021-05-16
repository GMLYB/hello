package com.lyb.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于返回前端通用的json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    // 200 success
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
