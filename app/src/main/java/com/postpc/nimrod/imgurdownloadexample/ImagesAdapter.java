package com.postpc.nimrod.imgurdownloadexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.postpc.nimrod.imgurdownloadexample.network.models.ImgurImageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>{


    private List<ImgurImageModel> images;

    ImagesAdapter() {
        this.images = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setImages(List<ImgurImageModel> images) {
        this.images = images;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_view)
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ImgurImageModel image) {
            Picasso.with(itemView.getContext())
                    .load(image.getLink())
                    .into(imageView);
        }
    }
}
