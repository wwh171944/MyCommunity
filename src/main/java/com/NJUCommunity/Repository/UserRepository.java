package com.NJUCommunity.Repository;

import com.NJUCommunity.PO.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserPO, Integer> {
    UserPO findByPhone(String phone);
}
