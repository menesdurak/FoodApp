package com.menesdurak.foodapp.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.foodapp.common.Constants
import com.menesdurak.foodapp.data.remote.dto.CartFood
import com.menesdurak.foodapp.data.remote.dto.CartFoodUi
import com.menesdurak.foodapp.databinding.ItemCartFoodBinding

class CartFoodAdapter(
    private val onItemLongClick: (Int, CartFoodUi) -> Unit
) : RecyclerView.Adapter<CartFoodAdapter.CartFoodHolder>() {

    private val cartFoods = mutableListOf<CartFoodUi>()
    private var totalPrice = 0

    inner class CartFoodHolder(private val binding: ItemCartFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(cartFoodUi: CartFoodUi) {
                with(binding) {
                    tvFood.text = cartFoodUi.foodName
                    tvPrice.text = cartFoodUi.price + " TL"
                    tvCount.text = "x " + cartFoodUi.count.toString()
                    tvTotalPrice.text = (cartFoodUi.price.toInt() * cartFoodUi.count).toString()

                    Glide
                        .with(root.context)
                        .load(Constants.FOOD_IMAGE + cartFoods[adapterPosition].image)
                        .into(ivFood)

                    root.setOnLongClickListener {
                        onItemLongClick.invoke(adapterPosition, cartFoodUi)
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

    fun updateCartFoodList(newList: List<CartFoodUi>) {
        cartFoods.clear()
        cartFoods.addAll(newList)
        notifyDataSetChanged()
        calculateTotalPrice()
    }

    fun removeCartFood(position: Int) {
        cartFoods.removeAt(position)
        notifyItemRemoved(position)
        calculateTotalPrice()
    }

    fun calculateTotalPrice(): Int {
        totalPrice = 0
        cartFoods.forEach {
            totalPrice += it.totalPrice
        }
        return totalPrice
    }
}