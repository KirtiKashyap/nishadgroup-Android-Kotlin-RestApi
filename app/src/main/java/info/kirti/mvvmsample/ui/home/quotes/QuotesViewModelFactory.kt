@file:Suppress("UNCHECKED_CAST")

package info.kirti.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.kirti.mvvmsample.data.repositries.QuotesRepository

class QuotesViewModelFactory(private val repository: QuotesRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}