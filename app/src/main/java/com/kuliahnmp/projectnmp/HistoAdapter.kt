package com.kuliahnmp.projectnmp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_card.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class HistoAdapter (
    val histories: ArrayList<History>, val productHistory: ArrayList<productHistory> ,val context: Context
)
    : RecyclerView.Adapter<HistoAdapter.ProductViewHolder>(){

    class ProductViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val orderID = v.findViewById<TextView>(R.id.txtOrderID)
        val qty = v.findViewById<TextView>(R.id.txtQty)
        val dateOrder = v.findViewById<TextView>(R.id.txtDate)
        val grandtotal = v.findViewById<TextView>(R.id.txtGrandTotal)
        val jumitem = v.findViewById<TextView>(R.id.txtJumlahJenis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.history_card, parent,false)
        return ProductViewHolder(v)
    }

    override fun getItemCount(): Int {
        return histories.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.v.txtOrderID.text = histories[position].orderid.toString()
        holder.v.txtJumlahJenis.text = histories[position].jumitem.toString()
        holder.v.txtDate.text= histories[position].orderdate.toString()
//        val formatter: NumberFormat = DecimalFormat("#,###")
//        val myNumber = histories[position].subtotal
//        val formattedNumber: String = formatter.format(myNumber)
//        holder.v.txtGrandTotal.text = "Rp."+ formattedNumber
        setProductRecycler(holder.v.historyProductView, productHistory, histories)

    }

    private fun setProductRecycler(recyclerView: RecyclerView, historyItem: ArrayList<productHistory>, histories: ArrayList<History>){
        val productAdapter = ProductHistoAdapter(historyItem,  histories, context)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = productAdapter
    }
}