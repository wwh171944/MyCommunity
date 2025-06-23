package com.NJUCommunity.PO;

import com.NJUCommunity.PO.Enum.Level;
import com.NJUCommunity.PO.Enum.Role;
import com.NJUCommunity.VO.UserVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserPO {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="role")
    private Role role;

    @Column(name="avatar_url")
    private String avatarUrl;

    @Column(name="level")
    private Level level;

    public UserPO(String userName, String password, String email, String phone, Role role, String avatarUrl, Level level) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.level = level;
    }
    public UserVO toVO() {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(this, userVO);
        return userVO;
    }
}
