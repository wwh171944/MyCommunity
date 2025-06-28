package com.NJUCommunity.Service;

import com.NJUCommunity.VO.UserVO;

public interface UserService {
    String register(UserVO user);
    String login(UserVO user);
    UserVO getUser(String token) throws Exception;
}
