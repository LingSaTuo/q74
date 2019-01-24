package com.celivad.q74.main.fragment.recommended

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.IntroductionActivity
import com.celivad.q74.R
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.ResultData
import com.celivad.widget.MovieItem
import kotlinx.android.synthetic.main.header_recommend.view.*
import kotlinx.android.synthetic.main.item_movie.view.*
import java.lang.Exception

open class RrecyclerAdapter : RecyclerView.Adapter<RrecyclerAdapter.Item>() {

    private val data = ArrayList<ResultBaseData>()

    fun addData(data: ResultBaseData) {
        this.data.add(data)
    }

    fun addData(arrayList: ArrayList<ResultBaseData>) {
        this.data.addAll(arrayList)
    }

    fun setData(arrayList: ArrayList<ResultBaseData>) {
        this.data.clear()
        addData(arrayList)
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Item {
        return when (p1) {
            ResultBaseData.AD -> {
                ADItem(LayoutInflater.from(p0.context).inflate(R.layout.ad_recommended, p0, false))
            }
            ResultBaseData.Header -> {
                Header(LayoutInflater.from(p0.context).inflate(R.layout.header_recommend, p0, false))
            }
            ResultBaseData.Result -> {
                MovieItem(LayoutInflater.from(p0.context).inflate(R.layout.item_movie, p0, false))
            }
            else -> {
                throw Exception()
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: Item, p1: Int) {
        p0.bindData(data[p1])
    }


    open class Item(private var view: View) : RecyclerView.ViewHolder(view) {
        open fun bindData(data: ResultBaseData) {
            view.setOnClickListener {
                IntroductionActivity.startMySelf(it.context, data)
            }
        }
    }

    private class Header(private var view: View) : Item(view) {
        override fun bindData(data: ResultBaseData) {
            HttpIconLoader(view.header).load(data.icon)
            super.bindData(data)
        }
    }

    private class MovieItem(var view: View) : Item(view) {
        override fun bindData(data: ResultBaseData) {
            view.title.text = data.title
            view.subtitle.text = data.uptime
            (view as com.celivad.widget.MovieItem).icon = data.icon
            super.bindData(data)
        }

    }

    private class ADItem(view: View) : Item(view) {

    }
}