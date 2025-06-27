package com.NJUCommunity.Controller;

import com.NJUCommunity.Service.UserService;
import com.NJUCommunity.VO.Response;
import com.NJUCommunity.VO.UserVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Response<String> register(@Valid @RequestBody UserVO user) {
        return Response.buildSuccess(userService.register(user));
    }
}
