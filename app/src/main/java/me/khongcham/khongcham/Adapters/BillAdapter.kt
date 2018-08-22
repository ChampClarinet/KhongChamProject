package me.khongcham.khongcham.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.khongcham.khongcham.Models.Items
import me.khongcham.khongcham.R
import me.khongcham.khongcham.Utils.Utils

class BillAdapter(var context: Context, var bill: ArrayList<Items>) : RecyclerView.Adapter<BillAdapter.GenericHolder>() {

    var totalPrice = 0.0

    init {
        for (bill in bill) {
            totalPrice += bill.pricePerUnit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_stock, parent, false)
        return ViewHolder(v, context)
    }

    override fun getItemCount(): Int {
        return bill.size
    }

    override fun onBindViewHolder(holder: GenericHolder, position: Int) {
        val item = bill[position]
        holder.setViewData(item)
    }

    class ViewHolder(v: View, var context: Context) : GenericHolder(v) {

        private val image: ImageView = v.findViewById(R.id.card_bill_image)
        private val label: TextView = v.findViewById(R.id.card_bill_label)
        private val pricePerUnit: TextView = v.findViewById(R.id.card_bill_price_per_pack)
        private val addButton: Button = v.findViewById(R.id.card_bill_quantity_add)
        private val removeButton: Button = v.findViewById(R.id.card_bill_quantity_remove)
        private val quantity: TextView = v.findViewById(R.id.card_bill_quantity)
        private val price: TextView = v.findViewById(R.id.card_bill_price)

        override fun setViewData(items: Items) {
            if (items.imageUrl != null)
                Glide.with(context)
                        .load(items.imageUrl)
                        .apply(RequestOptions().centerInside())
                        .into(image)
            label.text = items.name
            pricePerUnit.text = Utils.doubleToString(items.pricePerUnit)
            quantity.text = items.quantity.toString()
            price.text = items.getTotalPrice().toString()
            addButton.setOnClickListener {
                syncQuantity(++items.quantity)
            }
            removeButton.setOnClickListener {
                syncQuantity(--items.quantity)
            }
        }

        private fun syncQuantity(newQuantity: Int){
            quantity.text = newQuantity.toString()
        }

    }

    abstract class GenericHolder(v: View) : RecyclerView.ViewHolder(v) {

        abstract fun setViewData(items: Items)

    }

}