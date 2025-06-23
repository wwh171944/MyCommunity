package com.NJUCommunity.PO.Enum;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("管理员"),
    USER("普通用户");

    private final String description;

    Role(String description) {
        this.description = description;
    }

}
