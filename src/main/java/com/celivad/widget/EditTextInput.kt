package com.celivad.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import com.celivad.q74.R

class EditTextInput : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    @SuppressLint("Recycle")
    constructor(context: Context, attr: AttributeSet?, def: Int) : super(context, attr, def) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_edit, this, false) as ConstraintLayout
        val v = view.getChildAt(0)
        val vv = view.getChildAt(1)
        view.removeView(v)
        view.removeView(vv)
        addView(v)
        addView(vv)
        val obtain = context.obtainStyledAttributes(attr, R.styleable.EditTextInput)
        val icon = obtain.getResourceId(R.styleable.EditTextInput_icon, R.mipmap.report)
        val tint = obtain.getColor(R.styleable.EditTextInput_tint, Color.WHITE)
        val hint = obtain.getString(R.styleable.EditTextInput_hint) ?: ""
        val input = obtain.getInt(R.styleable.EditTextInput_android_inputType, EditorInfo.TYPE_NULL)
        inputType = input
        this.icon = icon
        this.hint = hint
        setBackgroundResource(R.drawable.login)
        this.iconTint = tint

    }

    var iconTint = Color.WHITE
        set(value) {
            (getChildAt(0) as ImageView).setColorFilter(value)
            (getChildAt(1) as AppCompatEditText).setTextColor(value)
            field = value
        }

    var inputType = EditorInfo.TYPE_NULL
    set(value) {
        (getChildAt(1) as AppCompatEditText).inputType = value
        field = value
    }

    var icon = R.mipmap.report
        set(value) {
            (getChildAt(0) as ImageView).setImageResource(value)
            field = value
        }

    var hint = ""
        set(value) {
            (getChildAt(1) as AppCompatEditText).hint = value
            field = value
        }
    var text = ""
        set(value) {
            (getChildAt(1) as AppCompatEditText).setText(value)
            field = value
        }
        get() {
            return (getChildAt(1) as AppCompatEditText).text?.toString() ?: ""
        }
}