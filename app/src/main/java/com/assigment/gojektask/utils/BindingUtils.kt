package com.assigment.gojektask.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("avatarUrl")
fun loadImage(
    image: ImageView,
    imageUrl: String
) {
    if (imageUrl.isNotEmpty()) {
        Glide.with(image.context)
            .load(imageUrl)
            .into(image)
    }

}

