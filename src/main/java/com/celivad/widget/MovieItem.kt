package com.celivad.widget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.R

class MovieItem : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, def: Int) : super(context, attr, def) {
    }

    var icon = ""
        set(value) {
            HttpIconLoader((getChildAt(0) as ImageView)).load(value)
            field = value
        }

    var title = ""
        set(value) {
            (getChildAt(1) as TextView).text = value
            field = value
        }

    var subtitle = ""
        set(value) {
            (getChildAt(2) as TextView).text = value
            field = value
        }
}