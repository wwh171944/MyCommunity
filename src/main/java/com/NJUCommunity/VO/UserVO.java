package com.NJUCommunity.VO;

import com.NJUCommunity.PO.Enum.Level;
import com.NJUCommunity.PO.Enum.Role;
import com.NJUCommunity.PO.UserPO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Integer id;

    @NotNull(message = "用户名不能为空")
    private String userName;

    @Size(min = 6, message = "密码长度不能小于6位")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Level level;

    private String avatarUrl;
    public UserPO toPO() {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(this, userPO);
        return userPO;
    }
}
