package com.hunseong.pokedex_clone.binding

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.hunseong.pokedex_clone.R
import com.hunseong.pokedex_clone.model.PokemonInfo
import com.hunseong.pokedex_clone.model.Result
import com.hunseong.pokedex_clone.utils.PokemonTypeUtils
import java.io.IOException

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToastResult(view: View, result: Result) {
        if (result is Result.Error) {
            if (result.exception is IOException) {
                Toast.makeText(view.context, R.string.network_error, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, R.string.something_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("toastThrowable")
    fun bindToastThrowable(view: View, throwable: Throwable?) {
        if (throwable != null) {
            if (throwable is IOException) {
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

    @JvmStatic
    @BindingAdapter("setPokemonType")
    fun bindSetPokemonType(view: ChipGroup, types: List<PokemonInfo.TypeResponse>?) {

        types?.forEach { typeResponse ->
            val type = typeResponse.type.name
            view.addView(
                Chip(view.rootView.context).apply {
                    isClickable = false
                    text = type
                    setChipBackgroundColorResource(PokemonTypeUtils.getTypeColor(type))
                }
            )
        }
    }
}