package com.kuliahnmp.projectnmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        btnSignupOnSG.setOnClickListener {
            if(txtPasswordSG.text.toString() == txtConfirmPassSG.text.toString()) {
                val q = Volley.newRequestQueue(this)
                val url = "http://ubaya.prototipe.net/nmp160418005/signupproses.php"
                val stringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener {
                        Log.d("cekparams", it)
                        val intent = Intent(this, LoginActivity::class.java);
                        startActivity(intent)
                        finish();
                    },
                    Response.ErrorListener {
                        Log.d("cekparams", it.message.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["email"] = txtEmailSG.text.toString();
                        params["username"] = txtUsernameSG.text.toString();
                        params["password"] = txtPasswordSG.text.toString();
                        return params
                    }
                }
                q.add(stringRequest)
            }
        }
    }
}