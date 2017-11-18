package com.cyberschnitzel.joinit.Domain.Response;

import java.util.ArrayList;

public class InterestResponse {
    ArrayList<String> interests;

    public InterestResponse(ArrayList<String> interests) {
        this.interests = interests;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }
}
