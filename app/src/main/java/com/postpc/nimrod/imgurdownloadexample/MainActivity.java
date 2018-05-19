package com.postpc.nimrod.imgurdownloadexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurImageModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.images_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.download_button)
    Button downloadButton;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        presenter.init();

    }

    @OnClick(R.id.download_button)
    public void onDownloadButtonClicked(){
        presenter.handleDownloadClicked();
    }

    @Override
    public void initRecyclerView(RecyclerView.Adapter adapter, int numOfColumnsInGrid) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumnsInGrid));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setProgressBarVisibility(int visible) {
        progressBar.setVisibility(visible);
    }

    @Override
    public void hideButton() {
        downloadButton.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
