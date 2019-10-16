package kr.ac.jejunu.ticket.adapter.bindingadapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class DataBindingAdapter {
    @BindingAdapter("imgSrc")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
