package com.celivad.q74.main.fragment.recommended

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.celivad.q74.MainActivity
import com.celivad.q74.R
import com.celivad.q74.SearchActivity
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.utils.BackgroundThread
import kotlinx.android.synthetic.main.fragment_recommended.view.*

class Recommended : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mview = inflater.inflate(R.layout.fragment_recommended, null, container != null)
        return mview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvadapter = RrecyclerAdapter()
        val mactivity = activity as MainActivity? ?: return
        view.appbar.setPadding(0, mactivity.statusBarHeight, 0, 0)
        view.recommended_rv.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        view.recommended_rv.adapter = rvadapter
        getData(view)
        view.search.setOnClickListener {
            val intent = Intent(mactivity, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData(view: View) {
        object : BackgroundThread<String, ArrayList<ResultBaseData>>("https://www.d6080.com/list/1/1.html") {
            override fun doInBackground(i: String): ArrayList<ResultBaseData> {
                return MovieListPageGet(i).list
            }
        }.excute {
            val adapter = (view.recommended_rv.adapter as RrecyclerAdapter)
            adapter.addData(HeaderItem.cast(it[0]))
            for (item in 1 until it.size){
                adapter.addData(MovieItem.cast(it[item]))
            }
            adapter.notifyDataSetChanged()
        }
    }
}