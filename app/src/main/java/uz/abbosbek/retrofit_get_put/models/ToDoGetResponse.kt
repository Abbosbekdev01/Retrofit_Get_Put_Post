package uz.abbosbek.retrofit_get_put.models

data class ToDoGetResponse(

    val bajarildi: Boolean,
    val batafsil: String,
    var id: Int?,
    var oxirgi_muddat: String,
    var sana: String,
    val sarlavha: String,
    val zarurlik: String
)
