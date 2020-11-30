package com.kuliahnmp.projectnmp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_card.view.*
import kotlinx.android.synthetic.main.history_product_card.view.*

class ProductHistoAdapter (val products: ArrayList<Product>,val context: Context)
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
        return products.size
    }

    override fun onBindViewHolder(holder: ProductHistoAdapter.ProductViewHolder, position: Int) {
        holder.v.txtJudul.text = products[position].judul
        holder.v.txtDeskripsi.text = products[position].deskripsi
//        holder.v.txtQty.text = products[position].qty
//        holder.v.txtHarga.text = products[position].harga


    }


}