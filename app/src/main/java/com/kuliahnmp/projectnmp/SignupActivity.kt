package com.kuliahnmp.projectnmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.json.JSONObject

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val actionBar : ActionBar? = supportActionBar
        actionBar!!.hide()
        btnSignupOnSG.setOnClickListener {
            if(txtEmailSG.text.toString() == "" || txtUsernameSG.text.toString() == "" || txtPasswordSG.text.toString() == "" || txtConfirmPassSG.text.toString()==""){
                Toast.makeText(
                    this,
                    "Input Data harus lengkap",
                    Toast.LENGTH_LONG
                ).show()

            }
            else {
                if (txtPasswordSG.text.toString() == txtConfirmPassSG.text.toString()) {
                    val q = Volley.newRequestQueue(this)
                    val url = "http://ubaya.prototipe.net/nmp160418005/signupproses.php"
                    val stringRequest = object : StringRequest(
                        Request.Method.POST, url,
                        Response.Listener<String> {
                            val obj = JSONObject(it)
                            if (obj.getString("result") == "OK") {
                                Log.d("ceksignup", it)
                                val intent = Intent(this, LoginActivity::class.java);
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Email atau Username sudah terpakai",
                                    Toast.LENGTH_LONG
                                ).show()
                                txtEmailSG.setText("")
                                txtPasswordSG.setText("")
                                txtConfirmPassSG.setText("")
                            }
                        },
                        Response.ErrorListener {
                            Log.d("ceksignup", it.message.toString())
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
                else {
                    Toast.makeText(
                        this,
                        "Password tidak sama",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        btnLoginOnSG.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java);
            startActivity(intent)
            finish()
        }
    }
}