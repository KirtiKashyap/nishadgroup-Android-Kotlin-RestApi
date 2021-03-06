package info.kirti.mvvmsample.data.network

import info.kirti.mvvmsample.data.network.responses.AuthResponse
import info.kirti.mvvmsample.data.network.responses.QuotesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {
    // http://192.168.0.31:8080"
    // this is my local machine address because my api is not deploy yet. If want my api code please go to github repository.
    // https://github.com/KirtiKashyap/nishad-group
    // quotes api is not implement at backend so when it will create I will update it soon.
    @GET("quotes")
    suspend fun getQuotes():Response<QuotesResponse>

    @FormUrlEncoded
    @POST("/api/login")// suspend function is a center of the coroutine and it pause and resume at a latter time this is use for long running operation and without blocking
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("/api/signup")
   suspend fun userSignup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String
    ): Response<AuthResponse>


    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi{

            val okHttpClient= OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            okHttpClient.readTimeoutMillis()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://192.168.0.31:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)

        }
    }
    }
