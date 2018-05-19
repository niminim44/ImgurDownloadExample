package com.postpc.nimrod.imgurdownloadexample;
import android.view.View;
import com.postpc.nimrod.imgurdownloadexample.network.ImgurConstants;
import com.postpc.nimrod.imgurdownloadexample.network.RetrofitServiceClientImgur;
import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurAlbumModel;
import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurImageModel;
import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurResponseModel;

import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

class MainPresenter implements MainContract.Presenter {

    private static final int NUM_OF_COLUMNS_IN_GRID = 3;
    private MainContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ImagesAdapter adapter;

    MainPresenter(MainContract.View view) {
        this.view = view;
    }


    @Override
    public void init() {
        view.setProgressBarVisibility(View.GONE);
        adapter = new ImagesAdapter();
        view.initRecyclerView(adapter, NUM_OF_COLUMNS_IN_GRID);
    }

    @Override
    public void handleDownloadClicked() {
        downloadImages();
    }

    @Override
    public void destroy() {
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

    private void downloadImages() {
        view.setProgressBarVisibility(View.VISIBLE);
        compositeDisposable.add(RetrofitServiceClientImgur.getImgurApi()
                .getChapterUpdates(ImgurConstants.CLIENT_ID, ImgurConstants.CATS_ALBUM_HASH_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::showImages)
                .subscribe());

    }

    private void showImages(Response<ImgurResponseModel> imgurResponse) {
        if(imgurResponse != null){
            ImgurResponseModel imgurResponseModel = imgurResponse.body();
            if(imgurResponseModel != null){
                ImgurAlbumModel album = imgurResponseModel.getAlbum();
                if(album != null){
                    putImagesInRecyclerView(album.getImages());
                }
                view.setProgressBarVisibility(View.GONE);
                view.hideButton();
            }
        }
    }

    private void putImagesInRecyclerView(List<ImgurImageModel> images) {
        adapter.setImages(images);
    }
}
