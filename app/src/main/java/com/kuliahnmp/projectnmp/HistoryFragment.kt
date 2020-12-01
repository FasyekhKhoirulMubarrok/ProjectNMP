package com.kuliahnmp.projectnmp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONObject
import java.util.ArrayList

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

    var v:View ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        (activity as MainActivity?)!!
//            .setActionBarTitle("History")
        bacadata()
    }
    fun bacadata(){
        val q = Volley.newRequestQueue(activity)
        val url = "http://ubaya.prototipe.net/nmp160418005/getHistov2.php"
        var stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("histrories", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val playObj = data.getJSONObject(i)
                        Log.e("playobj",playObj.toString())
                        val listProductHistor: ArrayList<productHistory> = ArrayList()
                        val listDetailHist = playObj.getJSONArray("detilproduct")
                        for (i in 0 until listDetailHist.length())
                        {
                            var product = listDetailHist.getJSONObject(i)
                            for(i in 0 until Global.productSementara.count())
                            {
                                if(Global.productSementara[i].id == product.getInt("products_id"))
                                {
                                    val testhist = productHistory(Global.productSementara[i].id,Global.productSementara[i].judul,Global.productSementara[i].deskripsi,Global.productSementara[i].image_url,Global.productSementara[i].harga,product.getInt("qty"))
                                    listProductHistor.add(testhist)
                                }
                            }

                        }
                        val history = History(
                            playObj.getInt("orderid"),
                            playObj.getString("dateOrder"),
                            playObj.getInt("jumItem"),
                            listProductHistor
                        )
                        Global.histories.add(history)
                    }
                    updateList()
                    Log.d("isihistory", Global.histories.toString())
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
//        Thread.sleep(3000)
//        // batas suci getProduct on history
//
//        val q1 = Volley.newRequestQueue(activity)
//        val url1 = "http://ubaya.prototipe.net/nmp160418005/getHistoProduct.php"
//        var stringRequest1 = object : StringRequest(
//            Request.Method.POST, url1,
//            Response.Listener<String> {
//                Log.d("prodhistori", it)
//                val obj = JSONObject(it)
//                if(obj.getString("result") == "OK") {
//                    val data = obj.getJSONArray("data")
//                    for(i in 0 until data.length()) {
//                        val playObj = data.getJSONObject(i)
//                        val productHistory = productHistory(
//                            playObj.getInt("id"),
//                            playObj.getString("judul"),
//                            playObj.getString("deskripsi"),
//                            playObj.getString("image_url"),
//                            playObj.getInt("harga")
//                        )
//                        Global.productHistories.add(productHistory)
//                    }
//                    updateList()
//                    Log.d("isiprodhistory", Global.productHistories.toString())
//                }
//            },
//            Response.ErrorListener {
//                Log.e("apiresult", it.message.toString())
//            })
//        {
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//
//                params["products_id"] = Global.histories[0].productId.toString()
//                return params
//            }
//        }
//        q1.add(stringRequest1)
    }
    override fun onResume() {
        super.onResume()

        Global.histories.clear()
        bacadata()
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
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        var recyclerView = v?.findViewById<RecyclerView>(R.id.histoView)
        recyclerView?.layoutManager = lm
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = HistoAdapter(Global.histories ,requireActivity())
        //Log.e("his",Global.histories.toString())
        Log.e("his",Global.histories[0].productHistories[0].judul.toString())
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