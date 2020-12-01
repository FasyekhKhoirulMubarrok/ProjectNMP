package com.kuliahnmp.projectnmp

import java.util.*
import kotlin.collections.ArrayList

data class History(val orderid: Int,val date:String,val jumItem:Int,  var productHistories:ArrayList<ProductHistory>)