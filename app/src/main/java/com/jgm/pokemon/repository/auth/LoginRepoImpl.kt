package com.jgm.pokemon.repository.auth

import com.google.firebase.auth.FirebaseUser
import com.jgm.pokemon.data.remote.auth.LoginDataSource

class LoginRepoImpl(private val dataSource: LoginDataSource): LoginRepository {
    override suspend fun signIn(email: String, password: String): FirebaseUser? {
        return dataSource.signIn(email, password)
    }

    //Lo de arriba es lo mismo que hacer esto:
    //override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)

}