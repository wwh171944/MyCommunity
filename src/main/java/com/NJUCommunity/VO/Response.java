package com.NJUCommunity.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应体用于后端向前端返回数据
 * @param <T>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public static <T> Response<T> buildSuccess(T result) {
        return new Response<T>("200", "请求成功", result);
    }

    public static <T> Response<T> buildFailure(String msg, String code) {
        return new Response<T>(code, msg, null);
    }

}
