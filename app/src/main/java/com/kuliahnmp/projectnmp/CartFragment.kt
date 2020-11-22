package com.kuliahnmp.projectnmp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {
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

        //updateList()
        Log.d("cekisiarray", Global.carts.toString())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_cart, container, false)
        //var s = v.findViewById<TextView>(R.id.txtSubtotal)
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        cartView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView

            adapter = CartAdapter(Global.carts, activity!!.applicationContext)

        }
    }

    override fun onResume() {
        super.onResume()
        var rv = v?.findViewById<RecyclerView>(R.id.cartView)
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        rv?.layoutManager = lm
        rv?.setHasFixedSize(true)
        if(activity!= null) {
            rv?.adapter = CartAdapter(Global.carts, activity!!.applicationContext)
        }
        val formatter: NumberFormat = DecimalFormat("#,###")
        var subTotalHargaFormat: String = formatter.format(Global.subTotalHarga)
        v?.txtSubtotal?.text = "Rp."+ subTotalHargaFormat
        v?.btnCheckout?.setOnClickListener{
            val q = Volley.newRequestQueue(activity!!.applicationContext)
            val url = "http://ubaya.prototipe.net/nmp160418005/addhistory.php" // ?id=1

            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    Log.d("cekparams", it)},
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString())

                }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    //params["productsid"] = Global.product[].toString()
                    params["usersid"] = Global.users[0].id.toString()
                    params["tanggalorder"] = Global.orderDate.toString()
                    params["qty"] = Global.qtyG.toString()
                    params["jmlItem"] = Global.carts.count().toString()
                    params["grandtotal"] = Global.subTotalHarga.toString()

                    return params
                }
            }

            q.add(stringRequest)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }

            }

    }
}