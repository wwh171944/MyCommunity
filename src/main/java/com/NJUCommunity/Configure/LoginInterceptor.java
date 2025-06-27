package com.NJUCommunity.Configure;

import com.NJUCommunity.Exception.CommunityException;
import com.NJUCommunity.Util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取token
        String token = request.getHeader("token");

        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            response.getWriter().write("don't provide token");
            throw CommunityException.noToken();
        }

        if (!tokenUtil.verifyToken(token)) {
            response.setStatus(401);
            response.getWriter().write("无效token");
            throw CommunityException.invalidToken();
        }

        return true;
    }
}
