package uz.abbosbek.retrofit_get_put.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {

    const val BASE_URL = "plans1.pythonanywhere.com/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitService():ApiService{

        return getRetrofit().create(ApiService::class.java)
    }
}