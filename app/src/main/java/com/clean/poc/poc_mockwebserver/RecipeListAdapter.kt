package com.clean.poc.poc_mockwebserver

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clean.poc.poc_mockwebserver.databinding.RecipeListRowBinding
import com.squareup.picasso.Picasso

class RecipeListAdapter(private var recipeModelList: List<Recipe>) :
    RecyclerView.Adapter<RecipeListAdapter.UserListViewHolder>() {

    inner class UserListViewHolder(val binding: RecipeListRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            RecipeListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val recipe = recipeModelList[position]
        holder.binding.tvFirstName.text = recipe.title
        holder.binding.tvLastName.text = recipe.publisher
        holder.binding.tvEmail.text = recipe.rating.toString()

        Picasso.get()
            .load(recipe.featuredImage)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(holder.binding.ivUserImage)


    }

    override fun getItemCount(): Int = recipeModelList.size


}