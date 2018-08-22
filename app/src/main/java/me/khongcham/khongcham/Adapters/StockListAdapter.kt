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
import me.khongcham.khongcham.Models.Stock
import me.khongcham.khongcham.R
import me.khongcham.khongcham.Utils.Utils

class StockListAdapter(var context: Context, var stockList: ArrayList<Stock>) : RecyclerView.Adapter<StockListAdapter.GenericHolder>() {

    var totalPrice = 0.0

    init {
        for (stock in stockList) {
            totalPrice += stock.pricePerUnit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_stock, parent, false)
        return ViewHolder(v, context)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    override fun onBindViewHolder(holder: GenericHolder, position: Int) {
        val stock = stockList[position]
        holder.setViewData(stock)
    }

    class ViewHolder(v: View, var context: Context) : GenericHolder(v) {

        private val image: ImageView = v.findViewById(R.id.card_stock_image)
        private val label: TextView = v.findViewById(R.id.card_stock_label)
        private val pricePerPack: TextView = v.findViewById(R.id.card_stock_price_per_pack)
        private val addButton: Button = v.findViewById(R.id.card_stock_quantity_add)
        private val removeButton: Button = v.findViewById(R.id.card_stock_quantity_remove)
        private val quantity: TextView = v.findViewById(R.id.card_stock_quantity)
        private val price: TextView = v.findViewById(R.id.card_stock_price)
        private val currentStock: TextView = v.findViewById(R.id.card_stock_current_stock)
        private val unitName: TextView = v.findViewById(R.id.card_stock_unit)
        private val profit: TextView = v.findViewById(R.id.card_stock_profit)

        override fun setViewData(stock: Stock) {
            if (stock.imageUrl != null)
                Glide.with(context)
                        .load(stock.imageUrl)
                        .apply(RequestOptions().centerInside())
                        .into(image)
            label.text = stock.name
            pricePerPack.text = Utils.doubleToString(stock.pricePerPack)
            quantity.text = "0"
            //val p = quantity.text.toString().toInt() * stock.pricePerUnit
            //price.text = Utils.doubleToString(p)
            //unitPerPack.text = stock.unitPerPack.toString()
            unitName.text = stock.unitName
            //val profit =
            addButton.setOnClickListener {
                var prevQuantity = quantity.text.toString().toInt()
                prevQuantity++
                quantity.text = prevQuantity.toString()
            }
            removeButton.setOnClickListener {
                var prevQuantity = quantity.text.toString().toInt()
                if (prevQuantity > 0) prevQuantity--
                quantity.text = prevQuantity.toString()
            }
        }

    }

    abstract class GenericHolder(v: View) : RecyclerView.ViewHolder(v) {

        abstract fun setViewData(stock: Stock)

    }

}