package com.github.api.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.api.data.model.User
import com.github.api.databinding.ItemMainBinding


class UserAdapter:RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list= arrayListOf<User>()

    private var onItemClickCallback:OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback=onItemClickCallback
    }

    fun setList(users:ArrayList<User>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(User:User){
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(User)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(User.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(itemAvatar)
                itemLogin.text = User.login
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: User)
    }
}