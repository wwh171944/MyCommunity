package com.NJUCommunity.Controller;

import com.NJUCommunity.Service.UserService;
import com.NJUCommunity.VO.Response;
import com.NJUCommunity.VO.UserVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Response<String> register(@Valid @RequestBody UserVO user) {
        return Response.buildSuccess(userService.register(user));
    }

    @PostMapping("/login")
    public Response<String> login(@Valid @RequestBody UserVO user) {
        return Response.buildSuccess(userService.login(user));
    }

    @GetMapping("/get")
    public Response<UserVO> getUser(@RequestHeader("token") String token) throws Exception {
        return Response.buildSuccess(userService.getUser(token));
    }
    @DeleteMapping("/delete")
    public Response<String> delete(@RequestHeader("token") String token) throws Exception {
        return Response.buildSuccess(userService.delete(token));
    }
}
