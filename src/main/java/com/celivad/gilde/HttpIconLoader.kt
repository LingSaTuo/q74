package com.celivad.gilde

import android.view.View
import android.widget.ImageView


/**
 * 图片加载器
 * */
class HttpIconLoader(private var imageView: ImageView) : Xglider(imageView) {
    override fun onFailed() {
    }
}