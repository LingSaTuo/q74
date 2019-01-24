package com.celivad.q74.search.fragment.results

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.R
import com.celivad.utils.ArrayUtils
import java.lang.Exception

class ResultsRvAdapter : RecyclerView.Adapter<ResultsRvAdapter.ResultItem>() {

    //是否正在加载更多
    private var loadmore = false


    private var loadMore: (ResultsRvAdapter) -> Unit = {}

    private var click: (View, ResultBaseData) -> Unit = { _, _ ->

    }

    fun setOnLoadMoreListener(loadMore: (ResultsRvAdapter) -> Unit) {
        this.loadMore = loadMore;
    }

    fun setOnClickListener(click: (View, ResultBaseData) -> Unit) {
        this.click = click
    }

    private val data = ArrayList<ResultBaseData>()

    fun addData(datas: ArrayList<ResultBaseData>) {
        if (ArrayUtils.isLoop(this.data,datas)){
            loadmore = true
            return
        }
        this.data.addAll(datas)
        loadmore = false
    }

    fun setData(datas: ArrayList<ResultBaseData>) {
        this.data.clear()
        addData(datas)
    }

    override fun onViewRecycled(holder: ResultItem) {
        super.onViewRecycled(holder)
        if (holder != null) {
            holder.recyled()
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ResultItem {
        return when (p1) {
            ResultBaseData.AD -> {
                AD(LayoutInflater.from(p0.context).inflate(R.layout.ad_recommended, p0, false))
            }
            ResultBaseData.Header -> {
                throw Exception()
            }
            ResultBaseData.Result -> {
                Result(LayoutInflater.from(p0.context).inflate(R.layout.item_movie, p0, false))
            }
            else -> {
                throw Exception()
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return this.data[position].getType()
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


    override fun onBindViewHolder(p0: ResultItem, p1: Int) {
        if (!loadmore){
            loadmore = true
            this.loadMore.invoke(this)
        }
        p0.bind(this.data[p1])
    }

    open class ResultItem(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(data: ResultBaseData) {

        }

        open fun recyled() {

        }
    }

    private inner class Header(view: View) : ResultItem(view) {

    }

    private inner class Result(private var view: View) : ResultItem(view) {
        override fun bind(data: ResultBaseData) {
            super.bind(data)
            view.setOnClickListener {
                click.invoke(view, data)
            }
            HttpIconLoader(view.findViewById(R.id.iv)).load(data.icon)
            view.findViewById<TextView>(R.id.title).text = data.title
            view.findViewById<TextView>(R.id.subtitle).text = data.uptime
        }

        override fun recyled() {
            super.recyled()
            Glide.with(view.context).clear(view.findViewById<ImageView>(R.id.iv))
        }
    }

    private inner class AD(view: View) : ResultItem(view) {

    }
}