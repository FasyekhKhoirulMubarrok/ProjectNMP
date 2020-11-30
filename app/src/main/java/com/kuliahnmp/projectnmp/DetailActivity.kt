package com.kuliahnmp.projectnmp

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.product_card_layout.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar :ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)



        var intent = intent
        val dJudul = intent.getStringExtra("dJudul")
        val dDeskripsi = intent.getStringExtra("dDeskripsi")
        val dKategori = intent.getStringExtra("dKategori")
        val dHarga = intent.getIntExtra("dHarga", 0)
        val dGambar = intent.getStringExtra("dGambar")
//
        actionBar.setTitle(Html.fromHtml("<font color='black'>" + dJudul + "</font>"))
        txtJudulDetail.text = dJudul
        txtDeskripsiDetail.text = dDeskripsi
        txtKategoriDetail.text = dKategori
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber = dHarga
        val formattedNumber: String = formatter.format(myNumber)
        txtHargaDetail.text = "Rp."+ formattedNumber
        Picasso.get().load(dGambar).into(imageView3)

        btnAdd2.setOnClickListener {
            var ada = false;
            var currentHarga = 0
            if(Global.carts.count()>0) {
                for (i in 0 until (Global.carts.size)) {
                    if (Global.carts[i].judul==dJudul) {
                        ada = true;
                        Global.carts[i].qty += 1;
                    }
                }
            }
            if(Global.carts.count()==0 || ada == false)
            {
                var product = Cart(dJudul.toString(),dDeskripsi.toString(),dGambar.toString(),dHarga,1)
                Global.carts.add(product)
            }
            if(Global.carts.count()>0) {
                for (i in 0 until (Global.carts.size)) {
                    Global.subTotalHarga = Global.carts[i].harga * Global.carts[i].qty;
                    //
                    currentHarga += Global.subTotalHarga
                }
            }
            Global.subTotalHarga = currentHarga
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}