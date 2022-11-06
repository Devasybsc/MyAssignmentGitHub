package com.example.myassignmentgithub.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myassignmentgithub.adapters.BaseBindableRecyclerViewAdapter

@BindingAdapter("itemViewModels")
fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<BindableItemViewModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(itemViewModels)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): BaseBindableRecyclerViewAdapter {
    val layoutManager =
        LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
    layoutManager.isSmoothScrollbarEnabled = true
    recyclerView.layoutManager = layoutManager
    return if (recyclerView.adapter != null && recyclerView.adapter is BaseBindableRecyclerViewAdapter) {
        recyclerView.adapter as BaseBindableRecyclerViewAdapter
    } else {
        val bindableRecyclerAdapter = BaseBindableRecyclerViewAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, image: String?) {
    image?.let {
        Glide.with(imageView.context)
            .load(it)
            .into(imageView)
    }
}
