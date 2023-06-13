package uz.abbosbek.retrofit_get_put

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.abbosbek.retrofit_get_put.adapters.MyToDoAdapter
import uz.abbosbek.retrofit_get_put.databinding.ActivityMainBinding
import uz.abbosbek.retrofit_get_put.models.ToDoGetResponse
import uz.abbosbek.retrofit_get_put.retrofit.ApiClient

class MainActivity : AppCompatActivity(), MyToDoAdapter.RvAction {
    private lateinit var myToDoAdapter: MyToDoAdapter
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var list: ArrayList<ToDoGetResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ApiClient.getRetrofitService()
            .getAllToDo()
            .enqueue(object :Callback<List<ToDoGetResponse>>{
                override fun onResponse(
                    call: Call<List<ToDoGetResponse>>,
                    response: Response<List<ToDoGetResponse>>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@MainActivity, "${response.body()}", Toast.LENGTH_SHORT)
                            .show()
                        list = ArrayList()
                        list.addAll(response.body()!!)
                        myToDoAdapter = MyToDoAdapter(list,this@MainActivity)
                        binding.rv.adapter = myToDoAdapter
                    }
                }

                override fun onFailure(call: Call<List<ToDoGetResponse>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun itemPopupClick(
        myToDoGetResponse: ToDoGetResponse,
        position: Int,
        imageView: ImageView
    ) {
        val popupMenu = PopupMenu(this, imageView)
        popupMenu.menuInflater.inflate(R.menu.my_popup_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_edit->{
                    ApiClient.getRetrofitService().deleteToDo(myToDoGetResponse.id)
                        .enqueue(object :Callback<String>{
                            override fun onResponse(
                                call: Call<String>,
                                response: Response<String>
                            ) {
                                if (response.isSuccessful){
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Delete doto",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Toast.makeText(this@MainActivity, "Error ${t.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        })
                }
                R.id.menu_delete ->{
                    val todo = myToDoGetResponse
                    todo.id = null
                    todo.oxirgi_muddat = "2023-06-12"
                    ApiClient.getRetrofitService().editToDo(myToDoGetResponse.id!!, todo)
                        .enqueue(object :Callback<ToDoGetResponse>{
                            override fun onResponse(
                                call: Call<ToDoGetResponse>,
                                response: Response<ToDoGetResponse>
                            ) {
                                if (response.isSuccessful){
                                    Toast.makeText(this@MainActivity, "Edit", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }

                            override fun onFailure(call: Call<ToDoGetResponse>, t: Throwable) {
                                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        })
                }
            }
            true
        }

        popupMenu.show()
    }
}