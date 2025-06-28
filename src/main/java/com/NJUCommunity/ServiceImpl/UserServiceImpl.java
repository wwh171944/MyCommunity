package com.NJUCommunity.ServiceImpl;

import com.NJUCommunity.Exception.CommunityException;
import com.NJUCommunity.PO.UserPO;
import com.NJUCommunity.Repository.UserRepository;
import com.NJUCommunity.Service.UserService;
import com.NJUCommunity.Util.TokenUtil;
import com.NJUCommunity.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String register(UserVO user) {
        try {
            UserPO new_user = userRepository.findByPhone(user.getPhone());
            if (new_user != null) {
                return "该手机号已注册";
            } else {
                new_user = user.toPO();
                new_user.setPassword(passwordEncoder.encode(user.getPassword())); // 密码加密
                userRepository.save(new_user);
            }
            return "注册成功";
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public String login(UserVO User) {
        try {
            UserPO user = userRepository.findByPhone(User.getPhone());
            if (user == null) {
                return "该手机号未注册";
            }
            if (!passwordEncoder.matches(User.getPassword(), user.getPassword())) {
                return "密码错误";
            }
            return tokenUtil.getToken(user);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public UserVO getUser(String token) throws Exception {
        UserPO user = tokenUtil.getUser(token);
        if (user == null) {
            throw CommunityException.userNotFound();
        }
        return user.toVO();
    }

    @Override
    public String delete(String token) throws Exception {
        UserPO user = tokenUtil.getUser(token);
        if (user == null) {
            throw CommunityException.userNotFound();
        }
        userRepository.delete(user);
        return "删除成功";
    }
}
