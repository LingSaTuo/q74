package com.celivad.widget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.celivad.q74.R
import android.view.inputmethod.InputMethodManager


class MSearchBar : ConstraintLayout {

    private var search: (String) -> Unit = {}
    private var click: (View) -> Unit = {}

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, def: Int) : super(context, attr, def) {
        val view = LayoutInflater.from(context).inflate(R.layout.search_bar, this, false) as ConstraintLayout
        addView(view)
        val tv = view.getChildAt(1) as AppCompatEditText
        tv.setOnEditorActionListener OnEditorActionListener@{ v, actionId, event ->
            if (actionId != 6) return@OnEditorActionListener true
            tv.text?.toString() ?: return@OnEditorActionListener true
            search.invoke(tv.text?.toString() ?: "")
            lastSearch = tv.text?.toString() ?: ""
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
            return@OnEditorActionListener true
        }
        view.getChildAt(0).setOnClickListener {
            this.click.invoke(view.getChildAt(0))
        }

    }

    fun onStartSearch(search: (String) -> Unit) {
        this.search = search
    }

    var lastSearch = ""

    fun onButtonClick(click: (View) -> Unit) {
        this.click = click
    }
}