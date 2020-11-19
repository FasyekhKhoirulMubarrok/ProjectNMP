package com.kuliahnmp.projectnmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
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
        actionBar.setTitle(dJudul)
        txtJudulDetail.text = dJudul
        txtDeskripsiDetail.text = dDeskripsi
        txtKategoriDetail.text = dKategori
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber = dHarga
        val formattedNumber: String = formatter.format(myNumber)
        txtHargaDetail.text = "Rp."+ formattedNumber
        Picasso.get().load(dGambar).into(imageView3)

    }
}