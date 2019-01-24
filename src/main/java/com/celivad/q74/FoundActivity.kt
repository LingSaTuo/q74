package com.celivad.q74

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.celivad.app.StateCompatActivity
import com.celivad.q74.found.FoundActivityInit
import com.celivad.q74.main.fragment.community.CollectionList
import com.celivad.q74.main.fragment.quote.QuoteItemD
import com.celivad.q74.search.fragment.Bus
import kotlinx.android.synthetic.main.activity_found.*

class FoundActivity : StateCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found)
        window.decorView.setBackgroundColor(resources.getColor(R.color.background_main))
        statusBarColor = Color.TRANSPARENT
        UI2Status()
        appbar.setPadding(0,statusBarHeight,0,0)
        FoundActivityInit(this).init()
    }

    companion object {
        fun startMyself(context: Context,data:QuoteItemD){
            val intent = Intent(context,FoundActivity::class.java)
            intent.putExtra("data",data)
            context.startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Bus.invoke("collection", CollectionList.list.list)
        CollectionList.save(this)
    }

}