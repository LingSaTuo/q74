package com.celivad.q74.main.fragment.quote

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.celivad.q74.FoundActivity
import com.celivad.q74.MainActivity
import com.celivad.q74.R
import kotlinx.android.synthetic.main.fragment_quote.view.*

class Quote : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mview = inflater.inflate(R.layout.fragment_quote,null,false)
        return mview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mactivity = activity as MainActivity??:return
        view.appbar.setPadding(0,mactivity.statusBarHeight,0,0)

        val adapter = QuoteRvAdapter()
        adapter.addData(QuoteHeaderD())
        QuoteDataGet(0).excute {
            adapter.addDatas(it)
            adapter.notifyDataSetChanged()
        }

        adapter.setOnClicklistener {
            FoundActivity.startMyself(mactivity,it)
        }
        val rv = view.quote_rv
        rv.layoutManager = StaggeredGridLayoutManager(1,LinearLayout.VERTICAL)
        rv.adapter = adapter
    }
}