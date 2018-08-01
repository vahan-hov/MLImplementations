package com.example.vahan.searchexample;

public class User {
    private String mImageUrl;
    private String mName;
    private String email;
    private String phoneNumber;
    private String description;

    public User(String mImageUrl, String mName, String email, String phoneNumber, String description) {
        this.mImageUrl = mImageUrl;
        this.mName = mName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
