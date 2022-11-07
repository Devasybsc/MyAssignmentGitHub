package com.example.myassignmentgithub.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myassignmentgithub.BR
import com.example.myassignmentgithub.databinding.BindableItemViewModel

class BaseBindableRecyclerViewAdapter(val listener: OnItemClickListener) :
    RecyclerView.Adapter<BaseBindableViewHolder>() {

    var itemViewModels: List<BindableItemViewModel> = emptyList()
    private val viewTypeToLayoutId: MutableMap<Int, Int> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindableViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewTypeToLayoutId[viewType] ?: 0,
            parent,
            false
        )
        return BaseBindableViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        val item = itemViewModels[position]
        if (!viewTypeToLayoutId.containsKey(item.viewType)) {
            viewTypeToLayoutId[item.viewType] = item.layoutId
        }
        return item.viewType
    }

    override fun getItemCount(): Int = itemViewModels.size

    override fun onBindViewHolder(holder: BaseBindableViewHolder, position: Int) {
        holder.bind(itemViewModels[position], listener)
    }

    fun updateItems(items: List<BindableItemViewModel>?) {
        itemViewModels = items ?: emptyList()
        notifyDataSetChanged()
    }
}

class BaseBindableViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(itemViewModel: BindableItemViewModel, listener: OnItemClickListener) {
        binding.setVariable(BR.searchUserViewModel, itemViewModel)
        binding.setVariable(BR.listener, listener)
    }
}