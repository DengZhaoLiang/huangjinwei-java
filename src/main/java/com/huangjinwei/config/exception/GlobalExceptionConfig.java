package com.huangjinwei.config.exception;

import com.huangjinwei.model.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionConfig {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        log.error("错误地址: {}  异常信息:", request.getRequestURI(), ex);
        Result result = new Result();
        result.setMessage(ex.getMessage());
        result.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }
}
