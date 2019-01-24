package com.celivad.q74.search.fragment.results

import com.celivad.q74.data.MovieData

abstract class ResultBaseData : MovieData() {
    abstract fun getType():Int

    companion object {
        val AD = 0
        val Result = 1
        val Header = 2
    }

}