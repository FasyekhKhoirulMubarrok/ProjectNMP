package com.kuliahnmp.projectnmp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.hide()
        //actionBar!!.setTitle(Html.fromHtml("<font color='black' >" + getString(R.string.app_name) + "</font>"))

        fun errorLog(view: View){
            val snack = Snackbar.make(view,"Invalid username and password.",Snackbar.LENGTH_LONG)
            snack.show()
        }
        btnLogin.setOnClickListener {
            val q = Volley.newRequestQueue(this)
            val url = "http://ubaya.prototipe.net/nmp160418005/loginproses.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> {
                    Log.d("cekparams", it)
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        val data = obj.getJSONArray("data")
                        for(i in 0 until data.length()) {
                            val playObj = data.getJSONObject(i)
                            val playlist = User(
                                playObj.getInt("id"),
                                playObj.getString("username"),
                                playObj.getString("password"),
                                playObj.getString("email")
                            )
                            Global.users.add(playlist)
                        }
                        Log.d("cekisiarray", Global.users.toString())
                        val intent = Intent(this, MainActivity::class.java);
                        startActivity(intent)
                        finish();
                    }
                    else{
                        errorLog(this.findViewById(R.id.layouts))
                        txtUsernameLogin.setText("")
                        txtPasswordLogin.setText("")
                    }
                },
                Response.ErrorListener {
                    Log.d("cekfail", it.message.toString())
                }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = txtUsernameLogin.text.toString();
                    params["password"] = txtPasswordLogin.text.toString();
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