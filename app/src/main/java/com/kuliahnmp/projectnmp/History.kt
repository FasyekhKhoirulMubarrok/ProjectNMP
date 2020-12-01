package com.kuliahnmp.projectnmp

import java.util.*
import kotlin.collections.ArrayList

data class History(val id:Int,
                   val orderid: Int,
                   val user_id: Int,
                val orderdate:String,
                val jumitem:Int, var productId: Int,var qty: Int,var subtotal:Int)