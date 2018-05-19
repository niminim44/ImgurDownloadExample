package com.postpc.nimrod.imgurdownloadexample.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImgurAlbumModel {

    private String title;

    @SerializedName("images_count")
    private String numOfImages;

    private List<ImgurImageModel> images;

    public ImgurAlbumModel() {
    }

    public ImgurAlbumModel(String title, String numOfImages, List<ImgurImageModel> images) {
        this.title = title;
        this.numOfImages = numOfImages;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public String getNumOfImages() {
        return numOfImages;
    }

    public List<ImgurImageModel> getImages() {
        return images;
    }
}
