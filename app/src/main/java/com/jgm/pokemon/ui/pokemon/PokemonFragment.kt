package com.jgm.pokemon.ui.pokemon

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jgm.pokemon.R
import com.jgm.pokemon.databinding.FragmentLoginBinding
import com.jgm.pokemon.databinding.FragmentPokemonBinding

class PokemonFragment : Fragment(R.layout.fragment_pokemon) {
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    private lateinit var binding: FragmentPokemonBinding
    private lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonBinding.bind(view)
        binding.toolbar.inflateMenu(R.menu.menu)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_signout -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_pokemonFragment_to_loginFragment)
                    true
                }
                else -> false
            }
        }
    }



}