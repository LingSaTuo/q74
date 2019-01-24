package com.celivad.q74.player

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.celivad.q74.R
import com.celivad.q74.search.fragment.results.ResultBaseData
import kotlinx.android.synthetic.main.item_history.view.*

class SelectionRVAdapter(private var adapter:RelateVideoAdapter) : RecyclerView.Adapter<SelectionRVAdapter.Item>() {
    private val data = ArrayList<ResultBaseData>()
    private var lis:(View, ResultBaseData,Int) -> Unit = {_,_,_->}
    init {
        data.clear()
        data.addAll(adapter.data)
    }
    fun setOnClickListener(lis:(View, ResultBaseData,Int) -> Unit){
        this.lis = lis
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Item {
        return MItem(LayoutInflater.from(p0.context).inflate(R.layout.item_history, p0, false))
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(p0: Item, p1: Int) {
        p0.bind(this.data[p1], p1)
    }

    open class Item(private var view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(data: ResultBaseData, index: Int) {
            view.titlei.text = data.title
        }
    }

    private inner class MItem(private var view: View):Item(view){
        override fun bind(data: ResultBaseData, index: Int) {
            super.bind(data, index)

            view.setOnClickListener {
                lis.invoke(it,data,index)
            }
        }
    }
}