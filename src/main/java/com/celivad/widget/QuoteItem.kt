package com.celivad.widget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.text.Html
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.main.fragment.quote.QuoteItem
import jp.wasabeef.glide.transformations.BlurTransformation
import com.bumptech.glide.request.RequestOptions



class QuoteItem : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, def: Int) : super(context, attr, def) {

    }

    fun setData(data:QuoteItem){
        setIcon(data.src)
        setTitle(data.title)
        setDesc(data.desc)
    }

    private fun setIcon(href:String){
        HttpIconLoader((getChildAt(0) as ImageView)).apply(RequestOptions.bitmapTransform(BlurTransformation(14, 4))).load(href)
        val cardview = getChildAt(1) as CardView
        HttpIconLoader(cardview.getChildAt(0) as ImageView).load(href)
    }

    fun cancleLoad(){
        val cardview = getChildAt(1) as CardView
        Glide.with(context).clear(getChildAt(0) as ImageView)
        Glide.with(context).clear(cardview.getChildAt(0) as ImageView)
    }

    private fun setTitle(title:String){
        (getChildAt(4) as TextView).text = title
    }
    private fun setDesc(title:String){
        (getChildAt(3) as TextView).text=""
        (getChildAt(3) as TextView).append(Html.fromHtml("<font color=\"#ffffff\">$title</font>"))
    }
}