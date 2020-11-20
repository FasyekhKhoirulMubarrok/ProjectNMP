package com.kuliahnmp.projectnmp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.history_card.view.*
import kotlinx.android.synthetic.main.product_card_layout.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class HistoAdapter (
    val products: ArrayList<Product>, val context: Context
)
    : RecyclerView.Adapter<HistoAdapter.ProductViewHolder>(){

    class ProductViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val judul = v.findViewById<TextView>(R.id.txtJudul)
        val deskripsi = v.findViewById<TextView>(R.id.txtDeskripsi)
        val kategori = v.findViewById<TextView>(R.id.txtKategori)
        val harga = v.findViewById<TextView>(R.id.txtHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoAdapter.ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.history_card, parent,false)
        return ProductViewHolder(v)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val url = products[position].image_url
        Picasso.get().load(url).into(holder.v.histoImg)
        holder.v.txtJudulHisto.text = products[position].judul
        holder.v.txtDescHisto.text = products[position].deskripsi
        holder.v.txtKateHisto.text = products[position].kategori
        //holder.v.txtJumlahHisto.text = products[position].kategori
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber = products[position].harga
        val formattedNumber: String = formatter.format(myNumber)
        holder.v.txtHargaHisto.text = "Rp."+ formattedNumber


    }
}