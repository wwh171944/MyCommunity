package com.NJUCommunity.ServiceImpl.User;

import com.NJUCommunity.PO.UserPO;
import com.NJUCommunity.Repository.UserRepository;
import com.NJUCommunity.ServiceImpl.UserServiceImpl;
import com.NJUCommunity.VO.UserVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void register_WhenPhoneNotRegistered_ShouldReturnSuccess() {
        // Arrange
        UserVO userVO = new UserVO();
        userVO.setPhone("13812345678");
        when(userRepository.findByPhone("13812345678")).thenReturn(null);

        // Act
        String result = userService.register(userVO);

        // Assert
        assertEquals("注册成功", result);
        verify(userRepository, times(1)).save(any(UserPO.class));
    }

    @Test
    void register_WhenPhoneAlreadyRegistered_ShouldReturnErrorMessage() {
        // Arrange
        UserVO userVO = new UserVO();
        userVO.setPhone("13812345678");
        UserPO existingUser = new UserPO();
        when(userRepository.findByPhone("13812345678")).thenReturn(existingUser);

        // Act
        String result = userService.register(userVO);

        // Assert
        assertEquals("该手机号已注册", result);
        verify(userRepository, never()).save(any(UserPO.class));
    }

    @Test
    void register_WhenPhoneIsNull_ShouldHandleGracefully() {
        // Arrange
        UserVO userVO = new UserVO();
        userVO.setPhone(null);
        when(userRepository.findByPhone(null)).thenReturn(null);

        // Act
        String result = userService.register(userVO);

        // Assert
        assertEquals("注册成功", result);
        verify(userRepository, times(1)).save(any(UserPO.class));
    }

    @Test
    void register_WhenPhoneIsEmpty_ShouldHandleGracefully() {
        // Arrange
        UserVO userVO = new UserVO();
        userVO.setPhone("");
        when(userRepository.findByPhone("")).thenReturn(null);

        // Act
        String result = userService.register(userVO);

        // Assert
        assertEquals("注册成功", result);
        verify(userRepository, times(1)).save(any(UserPO.class));
    }
}