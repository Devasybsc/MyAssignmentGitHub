package com.example.myassignmentgithub.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myassignmentgithub.R
import com.example.myassignmentgithub.adapters.BaseBindableRecyclerViewAdapter
import com.example.myassignmentgithub.adapters.OnItemClickListener
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter(value = ["itemViewModels", "listener"],  requireAll = false)
fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<BindableItemViewModel>?, listener: OnItemClickListener) {
    val adapter = getOrCreateAdapter(recyclerView, listener)
    adapter.updateItems(itemViewModels)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView, listener: OnItemClickListener): BaseBindableRecyclerViewAdapter {
    val layoutManager =
        LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
    layoutManager.isSmoothScrollbarEnabled = true
    recyclerView.layoutManager = layoutManager
    return if (recyclerView.adapter != null && recyclerView.adapter is BaseBindableRecyclerViewAdapter) {
        recyclerView.adapter as BaseBindableRecyclerViewAdapter
    } else {
        val bindableRecyclerAdapter = BaseBindableRecyclerViewAdapter(listener)
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

@BindingAdapter("setFavouriteCondition")
fun setFavouriteCondition(imageView: ShapeableImageView, isFavourite: LiveData<Boolean>?) {
    imageView.findViewTreeLifecycleOwner()?.let {
        isFavourite?.observe(
            it
        ) { it1 ->
            if (it1) {
                imageView.setImageResource(R.drawable.ic_favorite)
            } else {
                imageView.setImageResource(R.drawable.ic_favorite_border)
            }
        }
    }
}
