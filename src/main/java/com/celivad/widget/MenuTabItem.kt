package com.celivad.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.celivad.q74.R

class MenuTabItem : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attr: AttributeSet?, def: Int) : super(context, attr, def) {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        isClickable = true
        isFocusable = true
        setPadding(80, 40, 80, 80)
        val obtain = context.obtainStyledAttributes(attr, R.styleable.MenuTabItem)
        val icon = obtain.getResourceId(R.styleable.MenuTabItem_icon, R.mipmap.mov)
        val title = obtain.getString(R.styleable.MenuTabItem_title)
        val tint = obtain.getColor(R.styleable.MenuTabItem_tint, context.resources.getColor(R.color.colorAccent))
        val imageView = ImageView(context)
        imageView.setPadding(120, 40, 120, 0)
        imageView.setColorFilter(tint)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        val textView = TextView(context)
        textView.setTextColor(tint)
        textView.textSize = 16f;
        textView.gravity = Gravity.CENTER_HORIZONTAL
        addView(imageView)
        addView(textView)
        this.title = title?:""
        this.icon = icon
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            foreground = selectBackground
        }
    }

    var icon: Int = R.mipmap.tv
        set(value) {
            (getChildAt(0) as ImageView).setImageResource(value)
            field = value
        }

    var title: String = ""
        set(value) {
            (getChildAt(1) as TextView).text = value
            field = value
        }

    private var selectBackground: Drawable? = null
        get() {
            val typeValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typeValue, true)
            val attri = IntArray(1)
            attri[0] = android.R.attr.selectableItemBackground
            val array = context.theme.obtainStyledAttributes(typeValue.resourceId, attri);
            return array.getDrawable(0)
        }
}