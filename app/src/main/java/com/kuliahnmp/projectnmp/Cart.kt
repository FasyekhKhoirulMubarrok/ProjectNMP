package com.kuliahnmp.projectnmp

data class Cart(val id:Int,val judul:String,
                   val deskripsi:String,
                   val image_url:String,
                   val harga:Int, var qty:Int);