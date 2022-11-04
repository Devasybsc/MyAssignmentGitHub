package com.example.myassignmentgithub.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myassignmentgithub.R
import com.example.myassignmentgithub.network.model.UserShortInfo

class UserListAdapter :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val items = ArrayList<UserShortInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]
        val view = holder.itemView
//        view.setOnClickListener { onItemClick(user.serverId) }
//        view.login_text_view.text = user.login
        Glide.with(view.context).load(user.avatarUrl).into(view.avatar_image_view)
    }

    override fun getItemCount(): Int = items.size

    fun setItems(users: List<UserShortInfo>) {
        items.clear()
        items.addAll(users)
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
