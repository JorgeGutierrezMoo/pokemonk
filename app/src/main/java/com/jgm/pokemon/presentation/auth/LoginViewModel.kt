package com.jgm.pokemon.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.jgm.pokemon.core.Resource
import com.jgm.pokemon.repository.auth.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repo: LoginRepository): ViewModel() {


    fun signIn(email: String, password:String) = liveData(Dispatchers.IO) {
        emit((Resource.Loading()))
        try {
            emit(Resource.Success(repo.signIn(email, password)))
        } catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}

class LoginViewModelFactory(private val repo: LoginRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repo) as T
    }
}

