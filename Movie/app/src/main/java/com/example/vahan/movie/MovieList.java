package com.example.vahan.movie;

import android.widget.ImageView;

public class MovieList {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private ImageView image;
    private int rating;
    private boolean liked;
    private String url;
    private String description;

    public MovieList(String title, ImageView image, int rating, boolean liked, String url, String description) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.liked = liked;
        this.url = url;
        this.description = description;
    }


}
