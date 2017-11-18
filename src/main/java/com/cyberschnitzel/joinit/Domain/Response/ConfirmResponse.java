package com.cyberschnitzel.joinit.Domain.Response;

public class ConfirmResponse {
    private boolean loginResponse;

    public ConfirmResponse(boolean loginResponse) {
        this.loginResponse = loginResponse;
    }

    public boolean isLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(boolean loginResponse) {
        this.loginResponse = loginResponse;
    }
}
