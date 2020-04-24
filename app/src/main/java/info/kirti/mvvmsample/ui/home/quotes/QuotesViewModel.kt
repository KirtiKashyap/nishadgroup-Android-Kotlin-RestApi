package info.kirti.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModel
import info.kirti.mvvmsample.data.repositries.QuotesRepository
import info.kirti.mvvmsample.util.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {
    val quotes by lazyDeferred() {
        repository.getQuotes()
    }
}
