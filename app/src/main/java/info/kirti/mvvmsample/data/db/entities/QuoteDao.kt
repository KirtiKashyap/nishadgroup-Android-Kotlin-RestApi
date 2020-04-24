package info.kirti.mvvmsample.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuoteDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(quotes :List<Quote>)
    @Query("SELECT * FROM QUOTE")
    fun getQuote(): LiveData<List<Quote>>

}