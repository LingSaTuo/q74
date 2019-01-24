package com.celivad.q74.search.fragment.results

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.celivad.q74.IntroductionActivity
import com.celivad.q74.R
import com.celivad.q74.SearchActivity
import com.celivad.q74.main.fragment.recommended.MovieListPageGet
import com.celivad.q74.search.fragment.Bus
import com.celivad.utils.BackgroundThread
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_searchresults.view.*

class SearchResults : Fragment(), (ResultsRvAdapter) -> Unit {

    private var page = 0
    override fun invoke(adapter:ResultsRvAdapter) {
        val mactivity = activity as SearchActivity??:return
        GetMode(mactivity){
            adapter.addData(it)
        }.get()
    }

    companion object {
        val buskey = "searchresults"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_searchresults, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ResultsRvAdapter()
        adapter.setOnClickListener { v, resultBaseData ->
            IntroductionActivity.startMySelf(v.context,resultBaseData)
        }
        view.search_result_rv.layoutManager = StaggeredGridLayoutManager(1,LinearLayout.VERTICAL)
        view.search_result_rv.adapter = adapter
        Bus.add(buskey) {
            if (it is ArrayList<*>) {
                adapter.setData(it as ArrayList<ResultBaseData>)
                adapter.notifyDataSetChanged()
            }
        }
        adapter.setOnLoadMoreListener(this)
        super.onViewCreated(view, savedInstanceState)
    }
}