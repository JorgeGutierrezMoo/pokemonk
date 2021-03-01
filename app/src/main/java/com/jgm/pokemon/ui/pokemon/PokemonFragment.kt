package com.jgm.pokemon.ui.pokemon

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.jgm.pokemon.R
import com.jgm.pokemon.core.Resource
import com.jgm.pokemon.data.model.Pokemon
import com.jgm.pokemon.data.remote.RemotePokemonDataSource
import com.jgm.pokemon.databinding.FragmentPokemonBinding
import com.jgm.pokemon.presentation.PokemonViewModel
import com.jgm.pokemon.presentation.PokemonViewModelFactory
import com.jgm.pokemon.repository.RetrofitClient
import com.jgm.pokemon.repository.pokemon.PokemonRepositoryImplements
import com.jgm.pokemon.ui.pokemon.adapters.PokemonFragmentAdapter

class PokemonFragment : Fragment(R.layout.fragment_pokemon),
    PokemonFragmentAdapter.OnPokemonClickListener {
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    private lateinit var binding: FragmentPokemonBinding
    private val viewModel by viewModels<PokemonViewModel> {
        PokemonViewModelFactory(
            PokemonRepositoryImplements(
                RemotePokemonDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonBinding.bind(view)
        binding.toolbar.inflateMenu(R.menu.menu)
        setupRecyclerView()

        viewModel.fetchPokemon().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvPokemon.adapter =
                        PokemonFragmentAdapter(result.data.pokemonResultsList, this)
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }

        })

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

    /*
    private fun setupObservers(){
        viewModel.fetchPokemon().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvPokemon.adapter = PokemonFragmentAdapter(requireContext(), result.data)
                }
            }
        })
    }
    */

    private fun setupRecyclerView() {
        binding.rvPokemon.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    override fun onPokemonClick(pokemon: Pokemon) {
        Toast.makeText(
            requireContext(),
            "Added ${pokemon.name} to favourites!",
            Toast.LENGTH_SHORT
        )
            .show()
    }

}