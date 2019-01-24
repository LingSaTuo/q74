package com.celivad.q74.search

import com.celivad.q74.data.MovieBaseData
import com.celivad.q74.main.fragment.recommended.MovieListPageGet
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.ResultData
import org.jsoup.Jsoup

class SearchGet(private var i: String, private var page: String) : MovieListPageGet("${MovieBaseData.href}/?c=search&wd=$i&sort=addtime&order=desc&page=$page") {

}