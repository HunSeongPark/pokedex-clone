package com.hunseong.pokedex_clone.binding

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        if(!text.isNullOrEmpty()) {
            Toast.makeText(view.context, text, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun bindImage(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}