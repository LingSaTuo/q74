package com.celivad.q74.player

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.celivad.q74.R
import com.celivad.q74.introduction.IntroductionData
import com.celivad.q74.search.fragment.results.ResultBaseData
import kotlinx.android.synthetic.main.dialog_player_selection.view.*

class SelectionDia(private var data:IntroductionData,private var context: Context, private var madapter: RelateVideoAdapter,private var lis:(View, ResultBaseData,Int) -> Unit){
    private val view = LayoutInflater.from(context).inflate(R.layout.dialog_player_selection,null,false)
    private val dia:AlertDialog
    init {
        dia = AlertDialog.Builder(context)
            .setView(view)
            .show()
    }

    fun init(){
        dia.window?.setGravity(Gravity.BOTTOM)
        val adapter = SelectionRVAdapter(madapter)
        adapter.setOnClickListener { view, resultBaseData,index ->
            dia.dismiss()
            lis.invoke(view,resultBaseData,index)
        }
        view.layoutParams.height = context.resources.displayMetrics.heightPixels/2
        view.election_rv.layoutManager = StaggeredGridLayoutManager(8,LinearLayout.VERTICAL)
        view.election_rv.adapter = adapter
        view.election_rv.scrollToPosition(His.read(data.id))
    }
}