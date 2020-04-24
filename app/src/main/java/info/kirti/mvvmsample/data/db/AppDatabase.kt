package info.kirti.mvvmsample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.kirti.mvvmsample.data.db.entities.Quote
import info.kirti.mvvmsample.data.db.entities.QuoteDao
import info.kirti.mvvmsample.data.db.entities.User

@Database(entities = [User::class, Quote::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao(): QuoteDao

    companion object {
        // imediatly visible to other thread
        @Volatile
        private var instance :AppDatabase?=null
        private val LOCK=Any()
        operator fun invoke(context : Context)= instance?: synchronized(LOCK){
            instance?:builddatabae(context).also {
                instance=it
            }
        }
        private fun builddatabae(context: Context)=
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,"MyDatabase.db")
                .build()
    }

}