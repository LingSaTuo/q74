package com.celivad.q74.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.R
import com.celivad.q74.search.fragment.results.ResultBaseData
import com.celivad.q74.search.fragment.results.ResultData
import kotlinx.android.synthetic.main.item_relate_video.view.*
import java.lang.Exception

class RelateVideoAdapter : RecyclerView.Adapter<RelateVideoAdapter.RelateVItem>() {


    val data = ArrayList<ResultBaseData>()

    private var click:(View,ResultBaseData,Int)->Unit={_,_,_->}


    fun setOnClickListener(click:(View,ResultBaseData,Int)->Unit){
        this.click = click
    }

    fun addData(datas:ArrayList<ResultBaseData>){
        this.data.addAll(datas)
    }
    fun setData(datas:ArrayList<ResultData>){
        this.data.clear()
        this.data.addAll(datas)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RelateVItem {
        return when(p1){
            ResultBaseData.Result->{
                RelateItem(LayoutInflater.from(p0.context).inflate(R.layout.item_relate_video,p0,false))
            }
            ResultBaseData.AD->{
                RelateItem(LayoutInflater.from(p0.context).inflate(R.layout.ad_related_video,p0,false))
            }
            ResultBaseData.Header->{
                throw Exception()
            }
            else ->{
                throw Exception()
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: RelateVItem, p1: Int) {
        p0.bind(this.data[p1],p1)
    }

    open class RelateVItem(view:View):RecyclerView.ViewHolder(view){
        open fun bind(data:ResultBaseData,index:Int){}
    }

    private inner class RelateItem(private var view: View):RelateVItem(view){
        override fun bind(data: ResultBaseData,index:Int) {
            super.bind(data,index)
            view.setOnClickListener {
                His.add(data.id,index)
                click.invoke(it,data,index)
            }
            view.title.text = data.title
            HttpIconLoader(view.icon).load(data.icon)
        }

    }
    private inner class ADItem(private var view: View):RelateVItem(view){

    }
}