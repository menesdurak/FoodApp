package com.menesdurak.foodapp.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.menesdurak.foodapp.R
import com.menesdurak.foodapp.common.isValidEmail
import com.menesdurak.foodapp.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            val email = binding.etMail.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordConfirm = binding.etPasswordConfirm.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(
                    requireView(),
                    "Please enter an e-mail and password.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (!email.isValidEmail()) {
                Snackbar.make(
                    requireView(),
                    "Please enter a valid e-mail.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (password.length < 6) {
                Snackbar.make(
                    requireView(),
                    "Password length should be at least 5.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (password != passwordConfirm) {
                Snackbar.make(
                    requireView(),
                    "Passwords are not match.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                //Sign Up Success
                loginViewModel.signUp(email, password)
                findNavController().popBackStack()
            }

        }
    }

}