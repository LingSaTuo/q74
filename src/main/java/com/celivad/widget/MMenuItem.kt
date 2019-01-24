package com.celivad.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.celivad.q74.R

class MMenuItem : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attr: AttributeSet?, def: Int) : super(context, attr, def) {

        val obt = context.obtainStyledAttributes(attr,R.styleable.MMenuItem)
        val icon = obt.getResourceId(R.styleable.MMenuItem_icon,R.mipmap.setting)
        val title = obt.getString(R.styleable.MenuTabItem_title)?:""
        val view = LayoutInflater.from(context).inflate(R.layout.menu_item,this,false)
        addView(view)
        ((view as ConstraintLayout).getChildAt(0) as ImageView).setImageResource(icon)
        (view.getChildAt(1) as TextView).text = title
        isClickable = true
        isFocusable = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            foreground = selectBackground
        }
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