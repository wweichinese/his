package com.example.his.config;

import com.example.his.util.ResponseUtil;
import com.example.his.util.ResultMsg;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王伟
 * @date 2024/7/23 11:42
 * @desc 认证失败处理器AuthenticationFailureHandler
 * 同样的，一旦登录失败，比如用户名或者密码错误等等，则会调用AuthenticationFailureHandler进行处理，
 * 因此我们需要自定义一个认证失败的处理器，其中根据异常信息返回特定的JSON数据给客户端
 */
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    /**
     * 一旦登录失败则会被调用
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        //TODO 根据项目需要返回指定异常提示,这里演示了一个用户名密码错误的异常
        //BadCredentialsException 这个异常一般是用户名或者密码错误
        if (exception instanceof BadCredentialsException) {
            ResponseUtil.responseResult(response, new ResultMsg(401, "用户名或密码不正确！", null));
        }
        ResponseUtil.responseResult(response, new ResultMsg(401, "登录失败", null));
    }
}
