package com.celivad.q74.search.fragment.shapes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.celivad.q74.R
import com.celivad.q74.SearchActivity
import com.celivad.q74.search.fragment.Bus
import com.celivad.q74.search.fragment.results.SearchResults
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_searchshapes.view.*

class SearchShapes : Fragment() {
    private val map = HashMap<Int,String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        map[R.id.mov] = "1"
        map[R.id.tv] = "2"
        map[R.id.ct] = "3"
        map[R.id.terrorist] = "11"
        map[R.id.action] = "7"
        return inflater.inflate(R.layout.fragment_searchshapes,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mactivity = activity as SearchActivity??:return
        view.menugroup.setOnItemClickListener {
            mactivity.MODE = SearchActivity.Mode.TYPE
            mactivity.type = map[it.id]?:"1"
            Bus.invoke("lis",false)
            mactivity.TypeMore(1).excute {
                Bus.invoke("lis",true)
                Bus.invoke(SearchResults.buskey, it)
                mactivity.search_vp.currentItem = 0
            }
        }
    }
}