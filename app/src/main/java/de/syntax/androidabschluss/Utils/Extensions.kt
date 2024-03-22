package de.syntax.androidabschluss.Utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.glideImageSet(url:String){
    Glide.with(this.context)
        .load(url)
        .placeholder(null)
        .into(this)
}