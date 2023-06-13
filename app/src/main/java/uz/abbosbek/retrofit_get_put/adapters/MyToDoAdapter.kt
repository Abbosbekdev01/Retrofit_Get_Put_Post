package uz.abbosbek.retrofit_get_put.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.retrofit_get_put.databinding.ItemRvBinding
import uz.abbosbek.retrofit_get_put.models.ToDoGetResponse

class MyToDoAdapter(val list: ArrayList<ToDoGetResponse>, val rvAction: RvAction) : RecyclerView.Adapter<MyToDoAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(myToDoGetResponse: ToDoGetResponse, position: Int) {
            itemRv.tvId.text = myToDoGetResponse.id.toString()
            itemRv.tvSarlavha.text = myToDoGetResponse.sarlavha
            itemRv.tvSana.text = myToDoGetResponse.sana
            itemRv.tvBajarildi.text = myToDoGetResponse.bajarildi.toString()
            itemRv.tvBatafsil.text = myToDoGetResponse.batafsil
            itemRv.tvZarurligi.text = myToDoGetResponse.zarurlik
            itemRv.tvOxirgiMuddat.text = myToDoGetResponse.oxirgi_muddat

            itemRv.imagePopup.setOnClickListener {
                rvAction.itemPopupClick(myToDoGetResponse,position, itemRv.imagePopup)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    interface RvAction {
        fun itemPopupClick(myToDoGetResponse: ToDoGetResponse, position: Int, imageView: ImageView)
    }
}