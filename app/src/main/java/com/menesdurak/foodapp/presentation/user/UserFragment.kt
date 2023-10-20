package com.menesdurak.foodapp.presentation.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.databinding.FragmentDetailBinding
import com.menesdurak.foodapp.databinding.FragmentUserBinding
import com.menesdurak.foodapp.presentation.cart.CartUiState
import com.menesdurak.foodapp.presentation.cart.CartViewModel
import com.menesdurak.foodapp.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
   private lateinit var binding: FragmentUserBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    private val cartViewModel by viewModels<CartViewModel>()
    private var userName = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)

        userName = loginViewModel.getUserMail()

        setUserEMail()

        observeUiState()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibSignOut.setOnClickListener {
            loginViewModel.signOut()
            val action = UserFragmentDirections.actionUserFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    private fun setUserEMail() {
        binding.tvMail.text = loginViewModel.getUserMail()
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
                }

                is CartUiState.Success -> {
                    val badge =
                        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
                            .getOrCreateBadge(R.id.cart)
                    badge.number = it.data.size
                    badge.isVisible = badge.number > 0
                }
            }
        }
    }

}