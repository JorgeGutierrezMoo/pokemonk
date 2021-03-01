package com.jgm.pokemon.ui.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jgm.pokemon.R
import com.jgm.pokemon.core.Resource
import com.jgm.pokemon.data.remote.auth.LoginDataSource
import com.jgm.pokemon.databinding.FragmentLoginBinding
import com.jgm.pokemon.presentation.auth.LoginViewModel
import com.jgm.pokemon.presentation.auth.LoginViewModelFactory
import com.jgm.pokemon.repository.auth.LoginRepoImpl

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            LoginRepoImpl(
                LoginDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()
    }

    private fun isUserLoggedIn() {
        if (firebaseAuth.currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_pokemonFragment)
        }
    }

    private fun doLogin() {
        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().toString()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }

    private fun validateCredentials(email: String, password: String) {
        if (email.isEmpty()) {
            binding.editTextEmail.error = "E-mail field is empty"
            return
        }
        if (password.isEmpty()) {
            binding.editTextEmail.error = "Password field is empty"
            return
        }
        if (isValidEmail(email) == false) {
            binding.editTextEmail.error = "Use a valid E-mail"
            return
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return /*!TextUtils.isEmpty(email) &&*/ Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignin.isEnabled = false
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_pokemonFragment)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignin.isEnabled = true
                    Toast.makeText(
                        requireContext(), "Error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}