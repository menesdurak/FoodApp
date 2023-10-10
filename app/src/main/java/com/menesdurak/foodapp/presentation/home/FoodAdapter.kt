package com.menesdurak.foodapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.foodapp.common.Constants
import com.menesdurak.foodapp.common.Constants.FOOD_IMAGE
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.databinding.ItemFoodBinding

class FoodAdapter(
    private val onItemClick: (Food) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    private val foods = mutableListOf<Food>()

    inner class FoodHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(food: Food) {
                with(binding) {
                    tvFood.text = food.name
                    tvPrice.text = food.price

                    Glide
                        .with(root.context)
                        .load(FOOD_IMAGE + foods[adapterPosition].image)
                        .into(ivFood)

                    root.setOnClickListener {
                        onItemClick.invoke(food)
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

    fun updateFoods(newList: List<Food>) {
        foods.clear()
        foods.addAll(newList)
        notifyDataSetChanged()
    }
}