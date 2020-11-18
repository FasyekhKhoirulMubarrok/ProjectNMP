package com.kuliahnmp.projectnmp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_card_layout.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class ProductAdapter(
    val products: ArrayList<Product>, val context: Context
)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    class ProductViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val judul = v.findViewById<TextView>(R.id.txtJudul)
        val deskripsi = v.findViewById<TextView>(R.id.txtDeskripsi)
        val kategori = v.findViewById<TextView>(R.id.txtKategori)
        val harga = v.findViewById<TextView>(R.id.txtHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.product_card_layout, parent,false)
        return ProductViewHolder(v)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val url = products[position].image_url
        Picasso.get().load(url).into(holder.v.imageView)
        holder.v.txtJudul.text = products[position].judul
        holder.v.txtDeskripsi.text = products[position].deskripsi
        holder.v.txtKategori.text = products[position].kategori
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber = products[position].harga
        val formattedNumber: String = formatter.format(myNumber)
        holder.v.txtHarga.text = "Rp."+ formattedNumber

        holder.itemView.setOnClickListener{
            val model = products.get(position)
            var judul : String = model.judul
            var deskripsi : String = model.deskripsi
            var kategori : String = model.kategori
            var harga : Int = model.harga
            var gambar : String = model.image_url
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("dJudul",judul)
            intent.putExtra("dDeskripsi",deskripsi)
            intent.putExtra("dKategori",kategori)
            intent.putExtra("dHarga",harga)
            intent.putExtra("dGambar",gambar)
            context.startActivity(intent)
        }
        holder.v.btnAddToCart.setOnClickListener {
            val model = products.get(position)
            var ada = false;
            if(Global.carts.count()>0) {
                for (i in 0 until (Global.carts.size)) {
                    if (Global.carts[i].judul==model.judul) {
                        ada = true;
                        Global.carts[i].qty += 1;
                    }
                }
            }
            if(Global.carts.count()==0 || ada == false)
            {
                var product = Cart(model.judul,model.deskripsi,model.image_url,model.harga,1)
                Global.carts.add(product)
            }

            System.out.println("Jumlah Carts" + Global.carts.count())
            Log.d("isi carts", Global.carts.toString())

        }

    }




}