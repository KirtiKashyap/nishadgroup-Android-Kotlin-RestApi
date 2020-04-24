package info.kirti.mvvmsample.data.repositries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.kirti.mvvmsample.data.db.AppDatabase
import info.kirti.mvvmsample.data.db.entities.Quote
import info.kirti.mvvmsample.data.network.MyApi
import info.kirti.mvvmsample.data.network.SafeApiRequest
import info.kirti.mvvmsample.data.prefrences.PrefrenceProvider
import info.kirti.mvvmsample.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
private val MINIMUM_INTERVAL=6
class QuotesRepository (
    private val api : MyApi,
    private val db : AppDatabase,
    private val pref : PrefrenceProvider) : SafeApiRequest(){

    private val quotes= MutableLiveData<List<Quote>>()
    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }
    suspend fun getQuotes() : LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuote()
        }
    }
    private suspend fun fetchQuotes(){
        val lastSavedAT=pref.getLastSavedAt()
        if(lastSavedAT==null || isFetchNeeded(LocalDateTime.parse(lastSavedAT))){
            val response= apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }

    }
    private fun isFetchNeeded(savedAt: LocalDateTime):Boolean{
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now()) > MINIMUM_INTERVAL
    }
    private fun saveQuotes(quote:List<Quote>) {
        Coroutines.io {
            pref.saveLastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quote)
        }

    }

}