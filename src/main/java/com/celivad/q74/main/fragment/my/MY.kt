package com.celivad.q74.main.fragment.my

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.celivad.q74.*
import kotlinx.android.synthetic.main.fragment_my.view.*
import kotlinx.android.synthetic.main.my_userlayout.view.*

class MY : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mactivity = activity as MainActivity??:return
        view.user_layout.setOnClickListener {
            val intent = Intent(mactivity, LoginActivity::class.java)
            mactivity.startActivity(intent)
        }
        view.love.setOnClickListener {
            startActivity(Intent(mactivity,LoveActivity::class.java))
        }
        view.history.setOnClickListener {
            startActivity(Intent(mactivity,HistoryActivity::class.java))
        }
        view.appbar.setPadding(0,mactivity.statusBarHeight,0,0)
    }
}