package com.NJUCommunity.Exception;

public class CommunityException extends RuntimeException {
    public CommunityException(String message) {
        super(message);
    }
    public static Exception noToken() {
        return new CommunityException("未提供token");
    }
    public static Exception invalidToken() {
        return new CommunityException("无效的token");
    }
    public static Exception userNotFound() {
        return new CommunityException("用户不存在");
    }
}
