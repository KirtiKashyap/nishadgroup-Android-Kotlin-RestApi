package info.kirti.mvvmsample.ui.home.profile

import androidx.lifecycle.ViewModel
import info.kirti.mvvmsample.data.repositries.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {
    val user=repository.getUser()
}
