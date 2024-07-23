package com.example.his.config;

import com.example.his.util.ResponseUtil;
import com.example.his.util.ResultMsg;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王伟
 * @date 2024/7/23 11:56
 * @desc AccessDeniedHandler这处理器当认证成功的用户访问受保护的资源，但是权限不够，则会进入这个处理器进行处理。
 */
@Component
public class RequestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        ResponseUtil.responseResult(response, new ResultMsg(403, "权限不足！", null));
    }
}
