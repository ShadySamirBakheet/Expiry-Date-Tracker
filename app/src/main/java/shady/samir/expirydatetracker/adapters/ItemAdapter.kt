package shady.samir.expirydatetracker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.expirydatetracker.R
import shady.samir.expirydatetracker.data.models.Product
import shady.samir.expirydatetracker.services.Manage
import shady.samir.expirydatetracker.utils.Utils
import java.util.*


class ItemAdapter (private val context: Context?) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private lateinit var data: List<Product>
    var size =0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.apply {
            name.text = item.name
            category.text = item.category
            date.text =  getDateView(item.expiryDate)
            try {
                if (context != null && item.expiryDate.after(Date())) {
                    Manage.startNotify(context,item)
                }
            }catch (e:Exception){

            }
            itemView.setOnClickListener {
                // context?.startActivity(Intent(context, MealCategoryActivity::class.java))
            }

        }
    }

    /**
     * get Display Date For user
     * @param expiryDate
     */
    private fun getDateView(expiryDate: Date): CharSequence? {
        val date = Date()
        if (expiryDate.before(date) && expiryDate.date == date.date){
            return "${context?.getString(R.string.ExpiresToday)}"
        }

        if (expiryDate.before(date)){
            return "${context?.getString(R.string.Expires)} ${Utils().format.format(expiryDate)}"
        }

        if (expiryDate.year == date.year && expiryDate.month == date.month){
            return if (date.date == expiryDate.date ){
                "${getNum(expiryDate.hours - date.hours) }  ${context?.getString(R.string.hoursleft)}"
            }else{
                "${expiryDate.date - date.date} ${context?.getString(R.string.Daysleft)}"
            }
        }
        return Utils().format.format(expiryDate)
    }

    /**
     * return mock setting of hours
     * @param num of hours
     */
    private fun getNum(num: Int): Int {
        if (num in 0..6) return 12
        if (num in 6..12) return 12
        if (num in 12..18) return 12
        return 24
    }

    override fun getItemCount(): Int {
        return size
    }

    /**
     * set Adapter data
     * @param data is List of Products
     */
    fun addData(data: List<Product>?) {
        this.data = data!!
        size = data.size
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView =itemView.findViewById(R.id.name)
        var category: TextView =itemView.findViewById(R.id.category)
        var date: TextView =itemView.findViewById(R.id.date)
    }
}