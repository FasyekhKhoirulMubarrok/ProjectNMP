package com.kuliahnmp.projectnmp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val id:Int, val judul:String,
                    val deskripsi:String,
                    val kategori:String,
                    val image_url:String,
                    val harga:Int):Parcelable