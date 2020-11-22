package com.kuliahnmp.projectnmp

import okhttp3.internal.http.HttpDate.format
import java.lang.String.format
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

object Global {
    var users:ArrayList<User> = ArrayList()
    var carts:ArrayList<Cart> = ArrayList()
    var histories:ArrayList<History> = ArrayList()
    var qtyG:Int =0
    var subTotalHarga:Int=0
    var orderDate: Date = Date()
    // create a global variable which will hold your layout

}
