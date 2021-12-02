package com.hunseong.pokedex_clone.binding

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.hunseong.pokedex_clone.R
import com.hunseong.pokedex_clone.model.Result
import java.io.IOException

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, result: Result) {
        if (result is Result.Error) {
            if (result.exception is IOException) {
                Toast.makeText(view.context, R.string.network_error, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, R.string.something_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun bindImage(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .transition(withCrossFade())
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("isLoading")
    fun bindIsLoading(view: View, result: Result) {
        if (result is Result.Loading) {
            view.isVisible = true
        } else {
            view.isGone = true
        }
    }
}