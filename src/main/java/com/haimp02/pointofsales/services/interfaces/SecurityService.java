package com.haimp02.pointofsales.services.interfaces;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String email, String password);
}
