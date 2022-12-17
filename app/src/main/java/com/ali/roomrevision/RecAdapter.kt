package com.ali.roomrevision

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ali.roomrevision.databinding.CustomPostsLayoutBinding

class RecAdapter() : RecyclerView.Adapter<RecAdapter.PostViewHolder>() {
    var lst= emptyList<Post>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val bind = CustomPostsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(bind)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val models = lst.get(position)
            holder.binding.customTvId.text = models.id.toString()
            holder.binding.customTvTitle.text = models.title
            holder.binding.customTvBody.text = models.body
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun setlist(list: List<Post>) {
        this.lst = list
        notifyDataSetChanged()
    }

    class PostViewHolder(val binding: CustomPostsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}