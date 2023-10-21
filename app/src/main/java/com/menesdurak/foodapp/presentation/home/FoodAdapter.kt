package com.menesdurak.foodapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.common.Constants
import com.menesdurak.foodapp.common.Constants.FOOD_IMAGE
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.data.remote.dto.FoodUi
import com.menesdurak.foodapp.databinding.ItemFoodBinding

class FoodAdapter(
    private val onItemClick: (FoodUi) -> Unit,
    private val onFavoriteClick: (Int, FoodUi) -> Unit,
) : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    private val foods = mutableListOf<FoodUi>()
    private val favoriteIds = mutableListOf<Int>()

    inner class FoodHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(food: FoodUi) {
            with(binding) {
                tvFood.text = food.name
                tvPrice.text = food.price + " TL"

                Glide
                    .with(root.context)
                    .load(FOOD_IMAGE + foods[adapterPosition].image)
                    .into(ivFood)

                root.setOnClickListener {
                    onItemClick.invoke(food)
                }

                btnFavorite.setOnClickListener {
                    onFavoriteClick.invoke(adapterPosition, food)
                }

                if (food.id.toInt() in favoriteIds) {
                    food.isFavorite = true
                    btnFavorite.setImageDrawable(
                        binding.root.context.resources.getDrawable(
                            R.drawable.ic_baseline_favorite_24,
                            null
                        )
                    )
                } else {
                    food.isFavorite = false
                    btnFavorite.setImageDrawable(
                        binding.root.context.resources.getDrawable(
                            R.drawable.ic_baseline_favorite_border_24,
                            null
                        )
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val bind = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodHolder(bind)
    }

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(foods[position])
    }

    fun updateFoods(newList: List<FoodUi>) {
        foods.clear()
        foods.addAll(newList)
        notifyDataSetChanged()
    }

    fun updateFavoriteIds(newIdList: List<Int>) {
        favoriteIds.clear()
        favoriteIds.addAll(newIdList)
        notifyDataSetChanged()
    }

    fun changeFavoriteStatus(position: Int, id: Int) {
        if (id !in favoriteIds) {
            favoriteIds.add(id)
        } else {
            favoriteIds.remove(id)
        }
        foods[position].isFavorite = !foods[position].isFavorite
        notifyItemChanged(position)
    }
}