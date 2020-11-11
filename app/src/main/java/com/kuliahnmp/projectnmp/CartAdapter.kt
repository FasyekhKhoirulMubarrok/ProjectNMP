package com.kuliahnmp.projectnmp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_product_card_layout.view.*
import kotlinx.android.synthetic.main.product_card_layout.view.*
import kotlinx.android.synthetic.main.product_card_layout.view.imageView
import kotlinx.android.synthetic.main.product_card_layout.view.txtDeskripsi
import kotlinx.android.synthetic.main.product_card_layout.view.txtHarga
import kotlinx.android.synthetic.main.product_card_layout.view.txtJudul
import java.text.DecimalFormat
import java.text.NumberFormat

class CartAdapter(val carts: ArrayList<Cart>, val context: Context): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val judul = v.findViewById<TextView>(R.id.txtJudul)
        val deskripsi = v.findViewById<TextView>(R.id.txtDeskripsi)
        val harga = v.findViewById<TextView>(R.id.txtHarga)
        val qty = v.findViewById<TextView>(R.id.txtQty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.cart_product_card_layout, parent,false)
        return CartViewHolder(v)
    }

    override fun getItemCount(): Int {
        return carts.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val url = carts[position].image_url
        Picasso.get().load(url).into(holder.v.imageView)
        holder.v.txtJudul.text = carts[position].judul
        holder.v.txtDeskripsi.text = carts[position].deskripsi
        holder.v.txtQty.text= carts[position].qty.toString()
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber = carts[position].harga
        val formattedNumber: String = formatter.format(myNumber)
        holder.v.txtHarga.text = "Rp."+ formattedNumber
    }


}