package com.celivad.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import cn.jzvd.JzvdStd

class JZPlayer : JzvdStd {

    private var complete:(View)->Unit={}

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : super(context, attr){

    }

    fun setOnCompleteListener(complete:(View)->Unit){
        this.complete = complete
    }

    fun autoStart(){
        onClick(thumbImageView)
    }

    override fun onStateAutoComplete() {
        super.onStateAutoComplete()
        this.complete.invoke(this)
    }

    override fun onClick(v: View?) {
        if (jzDataSource != null && jzDataSource.urlsMap!=null)
            super.onClick(v)
    }
}