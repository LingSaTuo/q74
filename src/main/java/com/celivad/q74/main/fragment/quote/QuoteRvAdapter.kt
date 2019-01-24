package com.celivad.q74.main.fragment.quote

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.celivad.q74.FoundActivity
import com.celivad.q74.R
import com.celivad.widget.QuoteItem
import java.lang.Exception

class QuoteRvAdapter : RecyclerView.Adapter<QuoteRvAdapter.QuoteItem>() {

    private val data = ArrayList<QuoteItemD>()

    private var lis:(QuoteItemD)->Unit={}

    fun setOnClicklistener(lis:(QuoteItemD)->Unit){
        this.lis = lis;
    }

    fun addData(data: QuoteItemD){
        if (this.data.contains(data))return
        this.data.add(data)
    }

    fun setData(datas : ArrayList<QuoteItemD>){
        this.data.clear()
        addDatas(datas)
    }

    fun addDatas(datas : ArrayList<QuoteItemD>){
        this.data.addAll(datas)
    }

    override fun getItemViewType(position: Int): Int {
        return this.data[position].getType()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): QuoteItem {
       return when(p1){
           QuoteItemD.AD->{
                AD(LayoutInflater.from(p0.context).inflate(R.layout.ad_quote,p0,false))
           }
           QuoteItemD.HEADER->{
                Header(LayoutInflater.from(p0.context).inflate(R.layout.header_quote,p0,false))
           }
           QuoteItemD.ITEM->{
                Item(LayoutInflater.from(p0.context).inflate(R.layout.item_quotes,p0,false))
           }
           else ->{ throw Exception()}
       }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onViewRecycled(holder: QuoteItem) {
        super.onViewRecycled(holder)
        holder.recycled()
    }

    override fun onBindViewHolder(p0: QuoteItem, p1: Int) {
        p0.setData(data[p1])
    }

    open class QuoteItem(view: View) : RecyclerView.ViewHolder(view) {
        open fun setData(data:QuoteItemD){

        }

        open fun recycled(){

        }
    }

    private inner class Header(view: View): QuoteItem(view){

    }

    private inner class AD(view: View): QuoteItem(view){

    }

    private inner class Item(var view: View): QuoteItem(view){
        override fun setData(data: QuoteItemD) {
            data as com.celivad.q74.main.fragment.quote.QuoteItem
            (view as com.celivad.widget.QuoteItem).setData(data)
            super.setData(data)
            view.setOnClickListener{
                lis.invoke(data)
            }
        }

        override fun recycled() {
            super.recycled()
            (view as com.celivad.widget.QuoteItem).cancleLoad()
        }

    }
}