package info.kirti.mvvmsample.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    // Unit is like a void
    fun main(work :suspend (()->Unit))= CoroutineScope(Dispatchers.Main).launch{work()}

    fun io(work :suspend (()->Unit))= CoroutineScope(Dispatchers.IO).launch{work()}
}