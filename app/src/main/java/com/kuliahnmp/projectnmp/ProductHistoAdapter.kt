package com.kuliahnmp.projectnmp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.history_product_card.view.*
import kotlinx.android.synthetic.main.history_product_card.view.imageView
import kotlinx.android.synthetic.main.history_product_card.view.txtDeskripsi
import kotlinx.android.synthetic.main.history_product_card.view.txtHarga
import kotlinx.android.synthetic.main.history_product_card.view.txtJudul
import kotlinx.android.synthetic.main.product_card_layout.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class ProductHistoAdapter (val historyList: ArrayList<productHistory>, val context: Context)
    : RecyclerView.Adapter<ProductHistoAdapter.ProductViewHolder>()
{
    class ProductViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val judul = v.findViewById<TextView>(R.id.txtJudul)
        val qty = v.findViewById<TextView>(R.id.txtQty)
        val deskripsi = v.findViewById<TextView>(R.id.txtDeskripsi)
        val subtotal = v.findViewById<TextView>(R.id.txtSubtotal)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHistoAdapter.ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.history_product_card, parent,false)
        return ProductHistoAdapter.ProductViewHolder(v)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: ProductHistoAdapter.ProductViewHolder, position: Int) {
        val url = historyList[position].image_url
        Picasso.get().load(url).into(holder.v.imageView)
        holder.v.txtJudul.text = historyList[position].judul
        holder.v.txtDeskripsi.text = historyList[position].deskripsi
        var qty = historyList[position].qty
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber = qty * historyList[position].harga
        val formattedNumber: String = formatter.format(myNumber)
        holder.v.txtHarga.text = "Rp."+ formattedNumber
        holder.v.txtQty.text = qty.toString()


    }


}