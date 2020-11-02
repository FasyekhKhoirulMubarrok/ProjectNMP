package com.kuliahnmp.projectnmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val q = Volley.newRequestQueue(this)
            val url = "http://ubaya.prototipe.net/nmp160418005/loginproses.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    Log.d("cekparams", it)
                    val intent = Intent(this, MainActivity::class.java);
                    startActivity(intent)
                    finish();
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString())
                }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = txtUsername.text.toString();
                    params["password"] = txtPassword.text.toString();
                    return params
                }
            }
            q.add(stringRequest)
        }
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java);
            startActivity(intent)
        }
    }
}