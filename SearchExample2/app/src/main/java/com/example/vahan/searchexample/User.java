package com.example.vahan.searchexample;

public class User {
    private String mImageUrl;
    private String mName;

    public User(String mImageUrl, String mName) {
        this.mImageUrl = mImageUrl;
        this.mName = mName;
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
}
