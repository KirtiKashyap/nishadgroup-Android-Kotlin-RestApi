package info.kirti.mvvmsample.ui.auth

import info.kirti.mvvmsample.data.db.entities.User

interface AuthListener {
    fun onSuccess(user: User)
    fun onStarts()
    fun onFailure(message : String)
}