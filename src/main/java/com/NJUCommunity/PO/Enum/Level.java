package com.NJUCommunity.PO.Enum;

import lombok.Getter;

@Getter
public enum Level {
    LEVEL_0("潜水"),
    LEVEL_1("普通"),
    LEVEL_2("资深"),
    LEVEL_3("佬");

    private final String description;

    Level(String description) {
        this.description = description;
    }

}
