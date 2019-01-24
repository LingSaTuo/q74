package com.celivad.widget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

class MenuTabGroup : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, def: Int) : super(context, attr, def) {
    }

    fun setOnItemClickListener(lis: (MenuTabItem) -> Unit) {
        val size = childCount
        for (index in 0 until size) {
            val child = getChildAt(index)
            if (child is MenuTabItem){
                child.setOnClickListener {
                    lis.invoke(child)
                }
            }
        }
    }
}