package com.celivad.q74.search.fragment.results

import com.celivad.q74.SearchActivity
import kotlinx.android.synthetic.main.activity_search.*

class GetMode(
    private var mactivity: SearchActivity,
    private var lis: (ArrayList<ResultBaseData>) -> Unit
) {

    companion object {
        private var page = 1
        private var lastMode = SearchActivity.Mode.SEARCH
    }

    init {
        if (lastMode != mactivity.MODE) page = 1
    }

    fun get(): SearchActivity.Mode {
        if (mactivity.MODE == SearchActivity.Mode.SEARCH) {
            mactivity.SearchMore(mactivity.search_bar.lastSearch, ++page).excute(lis)
        }
        else{
            mactivity.TypeMore(++page).excute(lis)
        }
        lastMode = mactivity.MODE
        return mactivity.MODE
    }

}