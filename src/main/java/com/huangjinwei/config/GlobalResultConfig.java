package com.huangjinwei.config;

import com.huangjinwei.model.response.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@RestControllerAdvice(basePackages = "com.huangjinwei.controller")
public class GlobalResultConfig implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if (Objects.requireNonNull(methodParameter.getMethod()).getName().equals("upload")) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Result result = new Result();
        result.setStatus(HttpStatus.OK.value());
        result.setData(data);
        return result;
    }
}
