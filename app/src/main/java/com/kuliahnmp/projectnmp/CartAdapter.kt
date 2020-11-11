package com.kuliahnmp.projectnmp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_card_layout.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class CartAdapter(val products: ArrayList<Product>, val context: Context): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    class CartViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val judul = v.findViewById<TextView>(R.id.txtJudul)
        val deskripsi = v.findViewById<TextView>(R.id.txtDeskripsi)
        val harga = v.findViewById<TextView>(R.id.txtHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.product_card_layout, parent,false)
        return CartViewHolder(v)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val url = products[position].image_url
        Picasso.get().load(url).into(holder.v.imageView)
        holder.v.txtJudul.text = products[position].judul
        holder.v.txtDeskripsi.text = products[position].deskripsi
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber = products[position].harga
        val formattedNumber: String = formatter.format(myNumber)
        holder.v.txtHarga.text = "Rp."+ formattedNumber
    }


}