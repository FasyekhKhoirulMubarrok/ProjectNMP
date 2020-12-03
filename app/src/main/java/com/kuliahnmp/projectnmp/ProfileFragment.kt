package com.kuliahnmp.projectnmp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var v:View ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false)
        var email = v?.findViewById<TextInputEditText>(R.id.txtEmailProfile)
        var username = v?.findViewById<TextInputEditText>(R.id.txtUsernameProfile)
        email?.setText(Global.users[0].email)
        username?.setText(Global.users[0].username)


        val btn = v?.findViewById<ImageView>(R.id.btnChangeProfile)

        btn!!.setOnClickListener {
            if(txtOldPass.text.toString() == Global.users[0].password) {
                if (txtPasswordProfile.text.toString() == txtRepeatPasswordProfile.text.toString()
                ) {
                    val q = Volley.newRequestQueue(activity)
                    val url = "http://ubaya.prototipe.net/nmp160418005/updateUser.php"
                    val stringRequest = object : StringRequest(
                        Method.POST, url,
                        Response.Listener {
                            Log.d("cekparams", it)
                            Toast.makeText(
                                activity,
                                "Data Profile berhasil Diubah",
                                Toast.LENGTH_SHORT
                            ).show()
                            //Global.users[0].password = txtPasswordProfile.text.toString()
                        },
                        Response.ErrorListener {
                            Log.d("cekparams", it.message.toString())
                        }
                    ) {
                        override fun getParams(): MutableMap<String, String> {
                            val params = HashMap<String, String>()
                            params["email"] = txtEmailProfile.text.toString();
                            params["uid"] = Global.users[0].id.toString()
                            params["uname"] = txtUsernameProfile.text.toString();
                            params["pass"] = txtPasswordProfile.text.toString();
                            return params
                        }
                    }
                    q.add(stringRequest)
                } else {
                    Toast.makeText(activity, "Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }else
                Toast.makeText(activity, "Password lama salah", Toast.LENGTH_SHORT).show()
        }


        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)

                }
            }
    }
}