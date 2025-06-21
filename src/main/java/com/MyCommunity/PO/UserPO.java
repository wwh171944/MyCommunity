package com.MyCommunity.PO;

import com.MyCommunity.VO.UserVO;
import jakarta.persistence.*;
import jdk.jfr.Name;
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
    @Name("id")
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
    private String role;

    @Column(name="avatar_url")
    private String avatarUrl;

    @Column(name="level")
    private String level;

    public UserPO(String userName, String password, String email, String phone, String role, String avatarUrl, String level) {
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
