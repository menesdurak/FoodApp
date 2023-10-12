package com.menesdurak.foodapp.presentation.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.databinding.FragmentDetailBinding
import com.menesdurak.foodapp.databinding.FragmentUserBinding
import com.menesdurak.foodapp.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
   private lateinit var binding: FragmentUserBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignOut.setOnClickListener {
            loginViewModel.signOut()
        }
    }

}