package com.cyberschnitzel.joinit.Domain.Response;

public class AuthResponse {
    private boolean loginResponse;

    public AuthResponse(boolean loginResponse) {
        this.loginResponse = loginResponse;
    }

    public boolean isLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(boolean loginResponse) {
        this.loginResponse = loginResponse;
    }
}
