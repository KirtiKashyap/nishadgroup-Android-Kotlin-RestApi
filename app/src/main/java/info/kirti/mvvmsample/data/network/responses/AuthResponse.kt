package info.kirti.mvvmsample.data.network.responses

import info.kirti.mvvmsample.data.db.entities.User

data class AuthResponse (val isSuccess: Boolean?, val message: String?, val user: User)
