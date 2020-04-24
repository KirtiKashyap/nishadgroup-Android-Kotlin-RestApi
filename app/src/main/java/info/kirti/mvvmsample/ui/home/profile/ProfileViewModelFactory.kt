@file:Suppress("UNCHECKED_CAST")

package info.kirti.mvvmsample.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.kirti.mvvmsample.data.repositries.UserRepository

class ProfileViewModelFactory(private val repository: UserRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}