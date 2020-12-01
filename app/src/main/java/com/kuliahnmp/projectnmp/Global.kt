package com.kuliahnmp.projectnmp

import okhttp3.internal.http.HttpDate.format
import java.lang.String.format
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

object Global {

    var users:ArrayList<User> = ArrayList()
    var carts:ArrayList<Cart> = ArrayList()
    var histories:ArrayList<History> = ArrayList()
    var qtyG:Int =0
    var subTotalHarga:Int=0
    var productHistories: ArrayList<Product> = ArrayList()
    var orderId = 0
    var productSementara: ArrayList<Product> = ArrayList()
    // create a global variable which will hold your layout

}
