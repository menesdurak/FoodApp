package com.menesdurak.foodapp.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.foodapp.common.Constants
import com.menesdurak.foodapp.data.remote.dto.CartFood
import com.menesdurak.foodapp.databinding.ItemCartFoodBinding

class CartFoodAdapter(
    private val onItemLongClick: (Int, CartFood) -> Unit
) : RecyclerView.Adapter<CartFoodAdapter.CartFoodHolder>() {

    private val cartFoods = mutableListOf<CartFood>()

    inner class CartFoodHolder(private val binding: ItemCartFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(cartFood: CartFood) {
                with(binding) {
                    tvFood.text = cartFood.foodName
                    tvPrice.text = cartFood.price
                    tvCount.text = "x${cartFood.count}"

                    Glide
                        .with(root.context)
                        .load(Constants.FOOD_IMAGE + cartFoods[adapterPosition].image)
                        .into(ivFood)

                    root.setOnLongClickListener {
                        onItemLongClick.invoke(adapterPosition, cartFood)
                        true
                    }
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CartFoodAdapter.CartFoodHolder {
        val bind = ItemCartFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartFoodHolder(bind)
    }

    override fun onBindViewHolder(holder: CartFoodAdapter.CartFoodHolder, position: Int) {
        holder.bind(cartFoods[position])
    }

    override fun getItemCount(): Int = cartFoods.size

    fun updateCartFoodList(newList: List<CartFood>) {
        cartFoods.clear()
        cartFoods.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeCartFood(position: Int) {
        cartFoods.removeAt(position)
        notifyItemRemoved(position)
    }
}