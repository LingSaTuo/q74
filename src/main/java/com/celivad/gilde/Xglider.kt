package com.celivad.gilde

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import jp.wasabeef.glide.transformations.BlurTransformation


/**
 * 加载器抽象类
 * 用于加载图片，顺便降低耦合度
 * */
abstract class Xglider(private var imageView: ImageView) : DrawableImageViewTarget(imageView) {

    private val rm: RequestManager = Glide.with(imageView.context)

    private var loader: RequestBuilder<Drawable>? = null

    private var options = ArrayList<RequestOptions>()

    open fun load(url: String) {
        loader = rm.load(url)
        for (i in options) {
            loader?.apply(i)
        }
        loader?.into(this)
    }

    @SuppressLint("CheckResult")
    open fun apply(option: RequestOptions): Xglider {
        options.add(option)
        return this
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
        onFailed()
    }

    abstract fun onFailed()
}