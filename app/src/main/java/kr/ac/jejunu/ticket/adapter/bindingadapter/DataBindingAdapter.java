package kr.ac.jejunu.ticket.adapter.bindingadapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

public class DataBindingAdapter {
    @BindingAdapter("imgSrc")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(15)))
                .transition(new DrawableTransitionOptions().crossFade())
                .into(view);
    }
}
