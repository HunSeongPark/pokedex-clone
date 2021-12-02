package com.hunseong.pokedex_clone.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hunseong.pokedex_clone.model.Pokemon
import com.hunseong.pokedex_clone.model.Result
import com.hunseong.pokedex_clone.ui.adapter.PokemonAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            // 다른 Activity를 실행하고 돌아올 때 Adapter에 아이템이 1개 이상 있을 경우에만 Adapter의 Scroll 상태 등을 유지
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, result: Result) {
        if (result is Result.Success<*> && view.adapter is PokemonAdapter) {
            (view.adapter as ListAdapter<Any, *>).submitList(result.data as List<Pokemon>)
        }
    }
}