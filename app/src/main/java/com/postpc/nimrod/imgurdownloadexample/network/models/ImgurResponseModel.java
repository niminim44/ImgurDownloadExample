package com.postpc.nimrod.imgurdownloadexample.network.models;

import com.google.gson.annotations.SerializedName;

public class ImgurResponseModel {

    @SerializedName("data")
    private ImgurAlbumModel album;

    public ImgurResponseModel() {
    }

    public ImgurResponseModel(ImgurAlbumModel album) {
        this.album = album;
    }

    public ImgurAlbumModel getAlbum() {
        return album;
    }
}
