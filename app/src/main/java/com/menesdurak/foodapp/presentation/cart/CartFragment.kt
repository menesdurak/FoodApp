package com.menesdurak.foodapp.presentation.cart

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.common.convertToCartFoodUi
import com.menesdurak.foodapp.data.remote.dto.CartFoodUi
import com.menesdurak.foodapp.databinding.FragmentCartBinding
import com.menesdurak.foodapp.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val cartViewModel by viewModels<CartViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private var userName = ""
    private var totalPrice = 0
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

        val badge =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
                .getOrCreateBadge(R.id.cart)
        badge.isVisible = false

        userName = loginViewModel.getUserMail()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBuy.setOnClickListener {
            with(binding.animation) {
                visibility = View.VISIBLE
                playAnimation()
                addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator) { }

                    override fun onAnimationEnd(animation: Animator) {
                        visibility = View.GONE
                        val action = CartFragmentDirections.actionCartFragmentToHomeFragment()
                        findNavController().navigate(action)
                    }

                    override fun onAnimationCancel(animation: Animator) { }

                    override fun onAnimationRepeat(animation: Animator) { }

                })
            }


        }
    }

    override fun onResume() {
        super.onResume()

        observeUiState()

        setTotalPrice()
    }

    private fun observeUiState() {
        if (userName != "") {
            cartViewModel.getFoodsFromCart(userName)
        }
        cartViewModel.cartUiState.observe(viewLifecycleOwner) {
            when (it) {
                is CartUiState.Error -> {
                    Log.e("HomeViewModel", "Error: ${it.message}")
                }

                CartUiState.Loading -> {
                    binding.tvTotalPrice.visibility = View.INVISIBLE
                }

                is CartUiState.Success -> {
                    cartFoodAdapter.updateCartFoodList(it.data)
                    setTotalPrice()
                }
            }
        }
    }

    private fun onCartFoodLongClick(position: Int, cartFood: CartFoodUi) {
        Snackbar.make(
            requireView(),
            "Do you want to delete ${cartFood.foodName} from your cart?",
            Snackbar.LENGTH_SHORT
        )
            .setAction("YES") {
                cartFood.ids.forEach {
                    cartViewModel.deleteFoodFromCart(it.toInt(), userName)
                }
                cartFoodAdapter.removeCartFood(position)
                setTotalPrice()
            }.show()
    }

    private fun setTotalPrice() {
        totalPrice = cartFoodAdapter.calculateTotalPrice()
        binding.tvTotalPrice.text =
            totalPrice.toString() + " TL"
        if (totalPrice == 0) {
            with (binding.btnBuy) {
                visibility = View.INVISIBLE
                isClickable = false
            }
            binding.tvTotalPrice.visibility = View.INVISIBLE
        } else {
            with (binding.btnBuy) {
                visibility = View.VISIBLE
                isClickable = true
            }
            binding.tvTotalPrice.visibility = View.VISIBLE
        }
    }

}