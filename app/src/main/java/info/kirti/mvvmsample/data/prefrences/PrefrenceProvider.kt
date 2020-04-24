package info.kirti.mvvmsample.data.prefrences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
private const val KEY_SAVED_AT="key"
class PrefrenceProvider (context: Context){
    //Context in a constructor may prevent memory leak
   private val appContext=context.applicationContext
    private val preference : SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedAt : String){
        preference.edit().putString(KEY_SAVED_AT,savedAt).apply()

    }

    fun getLastSavedAt(): String ?{
        return preference.getString(KEY_SAVED_AT,null)
    }

}