package com.NJUCommunity.Util;

import com.NJUCommunity.PO.UserPO;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component

public class SecurityUtil {

    HttpServletRequest httpServletRequest;

    public UserPO getCurrentUser(){
        return (UserPO)httpServletRequest.getSession().getAttribute("currentUser");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();
    }
}
