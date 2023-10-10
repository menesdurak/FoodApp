package com.menesdurak.foodapp.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.common.Constants
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.databinding.FragmentDetailBinding
import com.menesdurak.foodapp.presentation.cart.CartUiState
import com.menesdurak.foodapp.presentation.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var food: Food
    private var userName = "numan123"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val bundle: DetailFragmentArgs by navArgs()
        food = bundle.food

        Glide
            .with(requireContext())
            .load(Constants.FOOD_IMAGE + food.image)
            .into(binding.ivFood)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            ivDecrease.setOnClickListener {
                if (tvCount.text.toString().toInt() > 1) {
                    tvCount.text = tvCount.text.toString().toInt().plus(-1).toString()
                }
            }
            ivIncrease.setOnClickListener {
                tvCount.text = tvCount.text.toString().toInt().plus(1).toString()
            }
            btnAddToCart.setOnClickListener {
                detailViewModel.postFoodsToCart(
                    food.name,
                    food.image,
                    food.price.toInt(),
                    tvCount.text.toString().toInt(),
                    userName
                )
                observeUiState()
            }
        }
    }

    private fun observeUiState() {
        detailViewModel.detailUiState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailUiState.Error -> {
                    Log.e("HomeViewModel", "Error: ${it.message}")
                }

                DetailUiState.Loading -> {
                    Log.e("HomeViewModel", "Loading")
                }

                is DetailUiState.Success -> {
                    Log.e("HomeViewModel", "Success: ${it.data.message}")
                }
            }
        }
    }

}