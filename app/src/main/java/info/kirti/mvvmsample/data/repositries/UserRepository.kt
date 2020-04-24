package info.kirti.mvvmsample.data.repositries

import info.kirti.mvvmsample.data.db.AppDatabase
import info.kirti.mvvmsample.data.db.entities.User
import info.kirti.mvvmsample.data.network.MyApi
import info.kirti.mvvmsample.data.network.SafeApiRequest
import info.kirti.mvvmsample.data.network.responses.AuthResponse

class UserRepository(private val api : MyApi, private val db : AppDatabase) : SafeApiRequest(){
    suspend fun userLogin(email : String, password : String) : AuthResponse{
    return apiRequest{api.userLogin(email,password)}
    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String,
        phone: String
    ) : AuthResponse{
        return apiRequest { api.userSignup(name,email,password,phone) }
    }
    suspend fun saveUser(user : User)= db.getUserDao().upsert(user)
    fun getUser()=db.getUserDao().getUser()
}