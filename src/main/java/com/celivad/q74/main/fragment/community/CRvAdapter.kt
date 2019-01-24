package com.celivad.q74.main.fragment.community

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.R
import com.celivad.q74.found.CommentD
import com.celivad.q74.found.CommentData
import kotlinx.android.synthetic.main.item_community.view.*

class CRvAdapter : RecyclerView.Adapter<CRvAdapter.CommunityItem>() {
    private val data = ArrayList<CommentData>()
    fun addData(data: CommentData){
        if (this.data.contains(data))return
        this.data.add(data)
    }

    fun setData(datas:ArrayList<CommentData>){
        this.data.clear()
        addData(datas)
    }

    fun addData(datas:ArrayList<CommentData>){
        this.data.addAll(datas)
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommunityItem {
        return CommunityItem(LayoutInflater.from(p0.context).inflate(R.layout.item_community,p0,false))
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(p0: CommunityItem, p1: Int) {
        p0.setData(this.data[this.data.size-1-p1])
    }

    open class CommunityItem(var view: View) : RecyclerView.ViewHolder(view){
        open fun setData(data: CommentD){
            view.titlei.text = data.title
            view.say.text = data.desc
            view.user.text = data.user
            if (data.usericon != "")
            HttpIconLoader(view.user_icon).load(data.usericon)
            HttpIconLoader(view.icon).load(data.icon)
        }
    }
}