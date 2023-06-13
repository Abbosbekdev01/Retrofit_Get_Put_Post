package uz.abbosbek.retrofit_get_put.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.abbosbek.retrofit_get_put.models.ToDoGetResponse

interface ApiService {

    @GET("plan")
    suspend fun getAllToDo():Call<List<ToDoGetResponse>>

    @PUT("plan/{id}/")
    fun editToDo(@Path("id") id:Int, @Body toDoResponse: ToDoGetResponse):Call<ToDoGetResponse>

    @DELETE("plan/{id}")
    fun deleteToDo(@Path("id") id: Int?):Call<String>

    @POST("plan/")
    fun addToDo(@Body toDoGetResponse: ToDoGetResponse):Call<ToDoGetResponse>

}