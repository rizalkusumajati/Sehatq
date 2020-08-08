package com.riztech.sehatq.util

import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.request.RequestOptions
import com.riztech.sehatq.BuildConfig
import com.riztech.sehatq.R
import com.riztech.sehatq.database.SehatqDatabase
import com.riztech.sehatq.widget.GlideApp
import com.riztech.sehatq.widget.MyGlideApp
import com.wajahatkarim3.roomexplorer.RoomExplorer

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
//    val options = RequestOptions()
//        .placeholder(progressDrawable)
//        .error(R.mipmap.ic_launcher_round)

    GlideApp.with(context)
        .load(uri)
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
        .into(this)
}

fun ImageView.loadImage(resource: Int?, progressDrawable: CircularProgressDrawable){
//    val options = RequestOptions()
//        .placeholder(progressDrawable)
//        .error(R.mipmap.ic_launcher_round)

    GlideApp.with(context)
        .load(resource)
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
        .into(this)
}

fun applyPixelToDp(context: Context, value: Int) : Int{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), context.resources.displayMetrics).toInt()
}
fun openRoom(context: Context){
    if (BuildConfig.DEBUG)
        RoomExplorer.show(context, SehatqDatabase::class.java, "sehatq.db")
}
