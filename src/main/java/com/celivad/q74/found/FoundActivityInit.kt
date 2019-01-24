package com.celivad.q74.found

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.celivad.q74.FoundActivity
import com.celivad.q74.main.fragment.quote.QuoteItemD
import kotlinx.android.synthetic.main.activity_found.*

class FoundActivityInit(private val foundActivity: FoundActivity) {
    private val data : QuoteItemD = foundActivity.intent.getSerializableExtra("data") as QuoteItemD
    fun init(){
        val adapter = FoundRVAdapter()
        foundActivity.found_rv.layoutManager = StaggeredGridLayoutManager(1,LinearLayout.VERTICAL)
        foundActivity.found_rv.adapter = adapter
        FoundDataGet(data.href,data).excute {
            adapter.addData(it)
            adapter.notifyDataSetChanged()
            foundActivity.loading.visibility = View.GONE
        }
    }
}