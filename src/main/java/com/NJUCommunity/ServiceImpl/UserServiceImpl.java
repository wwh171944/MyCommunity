package com.NJUCommunity.ServiceImpl;

import com.NJUCommunity.PO.UserPO;
import com.NJUCommunity.Repository.UserRepository;
import com.NJUCommunity.Service.UserService;
import com.NJUCommunity.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public String register(UserVO user) {
        logger.info("注册请求参数: {}", user);
        try {
            UserPO new_user = userRepository.findByPhone(user.getPhone());
            if (new_user != null) {
                return "该手机号已注册";
            } else {
                new_user = user.toPO();
            }
            userRepository.save(new_user);
            return "注册成功";
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
