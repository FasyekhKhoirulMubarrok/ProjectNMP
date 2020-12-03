package com.kuliahnmp.projectnmp

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.product_card_layout.view.*
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var products:ArrayList<Product> = ArrayList()
        val actionBar :ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)


        var intent = intent
        val dId = intent.getIntExtra("dId",0)

        val q = Volley.newRequestQueue(this)
        val url = "http://ubaya.prototipe.net/nmp160418005/productsDetail.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("cekparams", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val playObj = data.getJSONObject(i)
                        val product = Product(
                            playObj.getInt("id"),
                            playObj.getString("judul"),
                            playObj.getString("deskripsi"),
                            playObj.getString("kategori"),
                            playObj.getString("image_url"),
                            playObj.getInt("harga")
                        )
                        products.add(product)
                        Log.d("cekdetail", products.toString())
                        val dJudul = products[0].judul
                        val dDeskripsi = products[0].deskripsi
                        val dKategori = products[0].kategori
                        val dHarga = products[0].harga
                        val dGambar = products[0].image_url

                        actionBar.setTitle(Html.fromHtml("<font color='black'>" + dJudul + "</font>"))
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
            },
            Response.ErrorListener {
            }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["products_id"] = dId.toString()
                return params
            }
        }
        q.add(stringRequest)



        btnAdd2.setOnClickListener {
            var ada = false;
            var currentHarga = 0
            if(Global.carts.count()>0) {
                for (i in 0 until (Global.carts.size)) {
                    if (Global.carts[i].judul==products[0].judul) {
                        ada = true;
                        Global.carts[i].qty += 1;
                    }
                }
            }
            if(Global.carts.count()==0 || ada == false)
            {
                var product = Cart(dId,products[0].judul.toString(),products[0].deskripsi.toString(),products[0].image_url.toString(),products[0].harga,1)
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