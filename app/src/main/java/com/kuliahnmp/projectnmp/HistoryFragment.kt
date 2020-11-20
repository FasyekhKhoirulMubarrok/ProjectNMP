package com.kuliahnmp.projectnmp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var products:ArrayList<Product> = ArrayList()
    var v:View ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        (activity as MainActivity?)!!
//            .setActionBarTitle("History")
        val q = Volley.newRequestQueue(activity)
        val url = "http://ubaya.prototipe.net/nmp160418005/getHisto.php"
        var stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("histrores", it)
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
                    }
                    updateList()
                    Log.d("cekisiarray", products.toString())
                }
            },
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()

                params["uid"] = Global.users[0].id.toString()
                return params
            }
        }
        q.add(stringRequest)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_history, container, false)
        return v
    }

    fun updateList() {
        val gl: GridLayoutManager = GridLayoutManager(activity, 2)

        //val lm: LinearLayoutManager = LinearLayoutManager(activity)
        var recyclerView = v?.findViewById<RecyclerView>(R.id.histoView)
        recyclerView?.layoutManager = gl
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = HistoAdapter(products, requireActivity())

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}