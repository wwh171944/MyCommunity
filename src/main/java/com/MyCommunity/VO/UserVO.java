package com.MyCommunity.VO;

import com.MyCommunity.PO.UserPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {
    private Integer id;
    private String userName;
    private String password;
    private String role;
    private String email;
    private String phone;
    private String level;
    private String avatarUrl;
    public UserPO toPO() {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(this, userPO);
        return userPO;
    }
}
