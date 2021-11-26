package com.hunseong.pokedex_clone.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

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
    fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
        if(view.adapter != null) {
            (view.adapter as ListAdapter<Any, *>).submitList(itemList)
        }
    }
}