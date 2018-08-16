package com.example.vahan.searchexample;

public class User {
    private String mImageUrl;
    private String mName;
    private String email;

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

    public User(String mImageUrl, String mName, String email) {

        this.mImageUrl = mImageUrl;
        this.mName = mName;
        this.email = email;
    }
}
