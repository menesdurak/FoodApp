package com.menesdurak.foodapp.presentation.detail

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.menesdurak.foodapp.common.Constants
import com.menesdurak.foodapp.data.remote.dto.Food
import com.menesdurak.foodapp.data.remote.dto.FoodUi
import com.menesdurak.foodapp.databinding.FragmentDetailBinding
import com.menesdurak.foodapp.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel by viewModels<DetailViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var food: FoodUi
    private var userName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val bundle: DetailFragmentArgs by navArgs()
        food = bundle.foodUi

        binding.tvFoodName.text = food.name

        Glide
            .with(requireContext())
            .load(Constants.FOOD_IMAGE + food.image)
            .into(binding.ivFood)

        setTotalPrice(price = food.price)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName = loginViewModel.getUserMail()

        Log.e("Username", "View $userName")

        with(binding) {
            ivDecrease.setOnClickListener {
                if (tvCount.text.toString().toInt() > 1) {
                    tvCount.text = tvCount.text.toString().toInt().plus(-1).toString()
                    setTotalPrice(tvCount.text.toString().toInt(), food.price)
                }
            }
            ivIncrease.setOnClickListener {
                tvCount.text = tvCount.text.toString().toInt().plus(1).toString()
                setTotalPrice(tvCount.text.toString().toInt(), food.price)
            }
            btnAddToCart.setOnClickListener {
                if (userName != "") {
                    detailViewModel.postFoodsToCart(
                        food.name,
                        food.image,
                        food.price.toInt(),
                        tvCount.text.toString().toInt(),
                        userName
                    )
                    observeUiState()
                    animationView.playAnimation()
                    animationView.visibility = View.VISIBLE
                    animationView.addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {
                        }

                        override fun onAnimationEnd(animation: Animator) {
                            animationView.visibility = View.INVISIBLE
                            val action =
                                DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                            findNavController().navigate(action)
                        }

                        override fun onAnimationCancel(animation: Animator) {
                        }

                        override fun onAnimationRepeat(animation: Animator) {
                        }

                    })
                } else {
                    Snackbar.make(
                        requireView(),
                        "You need to sign in to add this to your cart. Do you want to sign in?",
                        Snackbar.LENGTH_SHORT
                    ).setAction("YES") {
                        val action = DetailFragmentDirections.actionDetailFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }.show()
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()

        userName = loginViewModel.getUserMail()

        Log.e("Username", "Resume $userName")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTotalPrice(count: Int = 1, price: String) {
        binding.tvTotalPrice.text = (count * price.toInt()).toString() + " TL"
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