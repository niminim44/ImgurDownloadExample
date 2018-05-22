package com.postpc.nimrod.imgurdownloadexample.network;

import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurAlbumModel;
import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurResponseModel;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ImgurApi {

    @GET("album/{id}")
    Single<Response<ImgurResponseModel>> getImgurAlbum(
            @Header("Authorization") String authorization,
            @Path("id") String id);

}
