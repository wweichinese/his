package com.example.his.config;

import com.example.his.util.ResponseUtil;
import com.example.his.util.ResultMsg;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王伟
 * @date 2024/7/23 11:52
 * @desc AuthenticationEntryPoint这个接口当用户未通过认证访问受保护的资源时，将会调用其中的commence()方法进行处理。
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ResponseUtil.responseResult(response, new ResultMsg(403, "认证失败，请重新登录！", null));
    }
}
