package com.kuliahnmp.projectnmp

import java.util.*

data class History(val id:Int,
                   val user_id: Int,
                val orderdate:String,
                val jumitem:Int,
                val subtotal:Int, var qty:Int);