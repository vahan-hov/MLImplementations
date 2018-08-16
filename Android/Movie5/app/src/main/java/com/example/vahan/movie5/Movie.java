package com.example.vahan.movie5;

public class Movie {

    private String title;
    private String description;
    private int image;
    private float rating;
    private boolean isLiked;
    private String url;

    public Movie(String title, String description, int image, float rating, boolean isLiked, String url) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.rating = rating;
        this.isLiked = isLiked;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public Movie () {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
