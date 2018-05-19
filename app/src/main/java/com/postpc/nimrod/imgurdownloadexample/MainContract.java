package com.postpc.nimrod.imgurdownloadexample;

import android.support.v7.widget.RecyclerView;

import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurAlbumModel;
import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurImageModel;

import java.util.List;

public interface MainContract {

    interface View{

        void initRecyclerView(RecyclerView.Adapter adapter, int numOfColumnsInGrid);

        void setProgressBarVisibility(int visible);

        void hideButton();
    }

    interface Presenter{

        void init();

        void handleDownloadClicked();

        void destroy();
    }

}
