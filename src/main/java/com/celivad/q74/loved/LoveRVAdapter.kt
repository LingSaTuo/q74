package com.celivad.q74.loved

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.IntroductionActivity
import com.celivad.q74.R
import com.celivad.q74.data.LoveList
import com.celivad.q74.data.MovieData
import com.celivad.q74.player.His
import kotlinx.android.synthetic.main.item_love.view.*

open class LoveRVAdapter :RecyclerView.Adapter<LoveRVAdapter.LoveItem>() {

    private val data =  ArrayList<MovieData>()

    protected var lis :(MovieData,Int)->Unit = {data,index->
        LoveList.remove(data)
        this@LoveRVAdapter.data.remove(data)
        notifyItemRemoved(index)
    }

    fun setData(data :  ArrayList<MovieData>){
        this.data.clear()
        this.data.addAll(data)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LoveItem {
        return LoveI(LayoutInflater.from(p0.context).inflate(R.layout.item_love,p0,false))
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(p0: LoveItem, p1: Int) {
        p0.bind(this.data[p1],p1)
    }



    open class LoveItem(var view:View):RecyclerView.ViewHolder(view){
        open fun bind(data: MovieData,index:Int){

        }
    }


   private inner class LoveI(view: View):LoveItem(view){
        @SuppressLint("SetTextI18n")
        override fun bind(data: MovieData,index: Int) {
            super.bind(data,index)
            HttpIconLoader( view.love_icon).load(data.icon)
            view.titlei.text = data.title
            view.sub.text = "播放到 ${His.read(data.id)} 集"
            view.loved.setOnClickListener {
                lis.invoke(data,index)
            }
            view.setOnClickListener {
                IntroductionActivity.startMySelf(view.context,data)
            }
        }
    }
}