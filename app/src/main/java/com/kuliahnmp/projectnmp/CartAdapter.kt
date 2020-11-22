package com.kuliahnmp.projectnmp

import android.content.Context
import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_product_card_layout.view.*
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

        val subtot = v.findViewById<TextView>(R.id.txtSubtotal)
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
        holder.v.btnMin.setOnClickListener{
            var currentHarga = 0
            carts[position].qty--
            var currentQty = carts[position].qty
            if(currentQty!=0){

                holder.v.txtQty.text = currentQty.toString()
                Global.carts[position].qty = currentQty
                if(Global.carts.count()>0) {
                    for (i in 0 until (Global.carts.size)) {

                        currentHarga += Global.carts[i].harga * Global.carts[i].qty;
                    }
                }
                Global.subTotalHarga -= currentHarga

            }else{
                Global.carts.remove(Global.carts[position])
                notifyItemRemoved(position)
            }
            var now = 0
            if(Global.carts.count()>0) {
                for (i in 0 until (Global.carts.size)) {
                    now += Global.carts[i].harga * Global.carts[i].qty;
                }
                Global.subTotalHarga = now
            }
            else{
                Global.subTotalHarga = 0
            }
            //holder.v.txtSubtotal.text = Global.subTotalHarga.toString()
        }
        holder.v.btnPlus.setOnClickListener{
            var currentHarga = 0
            var currentQty=0
            var qty = 0

            carts[position].qty++
            currentQty = carts[position].qty
            holder.v.txtQty.text = currentQty.toString()
            Global.carts[position].qty = currentQty
            if(Global.carts.count()>0) {
                for (i in 0 until (Global.carts.size)) {
                    Global.subTotalHarga = Global.carts[i].harga * Global.carts[i].qty;
                    //
                    currentHarga += Global.subTotalHarga
                    qty += Global.carts[i].qty
                }
            }
            Global.qtyG = qty
            Global.subTotalHarga = currentHarga

        }
        System.out.println("subtotal "+ Global.subTotalHarga)
    }


}