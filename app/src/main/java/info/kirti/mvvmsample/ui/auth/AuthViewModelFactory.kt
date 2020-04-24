@file:Suppress("UNCHECKED_CAST")

package info.kirti.mvvmsample.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.kirti.mvvmsample.data.repositries.UserRepository

class AuthViewModelFactory(private val repository: UserRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}