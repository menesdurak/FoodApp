package com.menesdurak.foodapp.presentation.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.data.remote.dto.CartFood
import com.menesdurak.foodapp.databinding.FragmentCartBinding
import com.menesdurak.foodapp.presentation.home.HomeUiState
import com.menesdurak.foodapp.presentation.home.HomeViewModel
import com.menesdurak.foodapp.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val cartViewModel by viewModels<CartViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private var userName = ""
    private val cartFoodAdapter by lazy { CartFoodAdapter(::onCartFoodLongClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        with(binding.recyclerView) {
            adapter = cartFoodAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        userName = loginViewModel.getUserMail()

        Log.e("Username", "$userName")

        if (userName != "") {
            cartViewModel.getFoodsFromCart(userName)
        }

        observeUiState()

        return binding.root
    }

    private fun observeUiState() {
        cartViewModel.cartUiState.observe(viewLifecycleOwner) {
            when (it) {
                is CartUiState.Error -> {
                    Log.e("HomeViewModel", "Error: ${it.message}")
                }

                CartUiState.Loading -> {
                    Log.e("HomeViewModel", "Loading")
                }

                is CartUiState.Success -> {
                    cartFoodAdapter.updateCartFoodList(it.data.cartFoodList)
                }
            }
        }
    }

    private fun onCartFoodLongClick(position: Int, cartFood: CartFood) {
        Snackbar.make(requireView(), "Do you want to delete ${cartFood.foodName} from your cart?", Snackbar.LENGTH_SHORT)
            .setAction("YES") {
                cartViewModel.deleteFoodFromCart(cartFood.foodId.toInt(), userName)
                cartFoodAdapter.removeCartFood(position)
            }.show()
    }

}