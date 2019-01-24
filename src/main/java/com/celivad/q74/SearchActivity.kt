package com.celivad.q74

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.celivad.app.StateCompatActivity
import com.celivad.q74.data.MovieBaseData
import com.celivad.q74.main.fragment.recommended.MovieListPageGet
import com.celivad.q74.search.SearchActivityInit
import com.celivad.q74.search.SearchGet
import com.celivad.q74.search.adapter.SearchVPAdapter
import com.celivad.q74.search.fragment.Bus
import com.celivad.q74.search.fragment.history.SearchHistory
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.SearchResults
import com.celivad.q74.search.fragment.shapes.SearchShapes
import com.celivad.utils.BackgroundThread
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : StateCompatActivity() {


    var MODE = Mode.SEARCH
    var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        Bus.clear(SearchResults.buskey)
        appbar.setPadding(0, statusBarHeight, 0, 0)
        statusBarColor = Color.TRANSPARENT
        UI2Status()
        window.decorView.setBackgroundColor(resources.getColor(R.color.background_main))

        val vadapter = SearchVPAdapter(supportFragmentManager)
        vadapter.addPage(SearchResults())
        vadapter.addPage(SearchHistory())
        vadapter.addPage(SearchShapes())
        search_vp.offscreenPageLimit = 3
        search_vp.adapter = vadapter
        search_vp.currentItem = 1
        SearchActivityInit(this).init()
        Bus.clear("lis")
        Bus.add("lis"){
            if (it is Boolean){
                if (it){
                    search_vp.currentItem = 0
                    loading.visibility = View.GONE
                }else{
                    loading.visibility = View.VISIBLE
                }
            }
        }
    }

    fun search(key:String){

        Bus.invoke("search",key)

        MODE = SearchActivity.Mode.SEARCH
        Bus.invoke("lis",false)
        SearchMore(key, 0).excute {
            Bus.invoke("lis",true)
            Bus.invoke(SearchResults.buskey, it)
            search_vp.currentItem = 0
        }
    }

    inner class SearchMore(private var i: String, private var page: Int) :
        BackgroundThread<String, ArrayList<ResultBaseData>>(i) {
        override fun doInBackground(i: String): ArrayList<ResultBaseData> {
            return SearchGet(i, "$page").list
        }
    }

    inner class TypeMore(page: Int):BackgroundThread<String, ArrayList<ResultBaseData>>("${MovieBaseData.href}/list/$type/${page}.html"){
        override fun doInBackground(i: String): ArrayList<ResultBaseData> {
            return MovieListPageGet(i).list
        }
    }


    enum class Mode{
        SEARCH,
        TYPE
    }
}