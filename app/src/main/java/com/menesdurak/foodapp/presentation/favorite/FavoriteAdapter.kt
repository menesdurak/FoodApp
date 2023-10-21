package com.menesdurak.foodapp.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.foodapp.common.Constants
import com.menesdurak.foodapp.data.local.entity.FavoriteFood
import com.menesdurak.foodapp.data.remote.dto.FoodUi
import com.menesdurak.foodapp.databinding.ItemFavoriteFoodBinding

class FavoriteAdapter(
    private val onItemClick: (FavoriteFood) -> Unit,
    private val onFavoriteClick: (Int, FavoriteFood) -> Unit,
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    private val favorites = mutableListOf<FavoriteFood>()

    inner class FavoriteHolder(private val binding: ItemFavoriteFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(food: FavoriteFood) {
            with(binding) {
                tvFood.text = food.foodName
                tvPrice.text = food.price + " TL"

                Glide
                    .with(root.context)
                    .load(Constants.FOOD_IMAGE + favorites[adapterPosition].image)
                    .into(ivFood)

                root.setOnClickListener {
                    onItemClick.invoke(food)
                }

                btnFavorite.setOnClickListener {
                    onFavoriteClick.invoke(adapterPosition, food)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val bind =
            ItemFavoriteFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHolder(bind)
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(favorites[position])
    }

    fun updateFavorites(newList: List<FavoriteFood>) {
        favorites.clear()
        favorites.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeFavorite(position: Int) {
        favorites.removeAt(position)
        notifyItemRemoved(position)
    }
}