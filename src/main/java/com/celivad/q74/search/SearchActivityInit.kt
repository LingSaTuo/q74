package com.celivad.q74.search

import com.celivad.q74.SearchActivity
import com.celivad.q74.data.MovieData
import com.celivad.q74.search.fragment.Bus
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.SearchResults
import com.celivad.utils.BackgroundThread
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivityInit(private var searchActivity: SearchActivity) {

    fun init() {
        bindlistener()
        searchActivity.search_bar.onStartSearch { i ->
            searchActivity.search(i)
        }

    }

    private fun bindlistener() {
        searchActivity.search_bar.onButtonClick {
            searchActivity.search_vp.currentItem = 2
        }
    }

}