package com.celivad.widget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

class MYUserGroup : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, def: Int) : super(context, attr, def) {
    }
}