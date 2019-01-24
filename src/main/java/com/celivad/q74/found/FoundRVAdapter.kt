package com.celivad.q74.found

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.celivad.gilde.HttpIconLoader
import com.celivad.q74.R
import com.celivad.q74.main.fragment.community.CollectionList
import kotlinx.android.synthetic.main.item_comment.view.*
import java.lang.Exception

class FoundRVAdapter : RecyclerView.Adapter<FoundRVAdapter.Item>() {

    private var data = ArrayList<CommentData>()

    fun addData(data: ArrayList<CommentData>) {
        this.data.addAll(data)
    }

    fun setData(data: ArrayList<CommentData>) {
        this.data.clear()
        addData(data)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Item {
        return when (p1) {
            CommentD.AD -> {
                AD(LayoutInflater.from(p0.context).inflate(R.layout.ad_recommended, p0, false))
            }
            CommentD.COMMENT -> {
                Comment(LayoutInflater.from(p0.context).inflate(R.layout.item_comment, p0, false))
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

    override fun onBindViewHolder(p0: Item, p1: Int) {
        p0.bind(this.data[p1])
    }

    override fun onViewRecycled(holder: Item) {
        super.onViewRecycled(holder)
        if (holder != null)
            holder.recycled()
    }

    open class Item(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(data: CommentData) {
        }

        open fun recycled() {

        }
    }

    private inner class Comment(private var view: View) : Item(view) {
        override fun bind(data: CommentData) {
            super.bind(data)
            if (data.usericon != ""){
                HttpIconLoader(((view as ConstraintLayout).getChildAt(0)as ImageView)).load(data.usericon)
            }
            if (CollectionList.contains(data)){
                view.subs.setColorFilter(view.resources.getColor(R.color.colorAccent))
            }else{
                view.subs.setColorFilter(Color.WHITE)
            }
            view.subs.setOnClickListener {
                Toast.makeText(it.context,if (changeCollection(view.subs,data)){"加入标签"}else{"取消标签"},Toast.LENGTH_LONG).show()
            }
            ((view as ConstraintLayout).getChildAt(1)as TextView).text = data.user
            ((view as ConstraintLayout).getChildAt(2)as TextView).text = ""
            ((view as ConstraintLayout).getChildAt(2)as TextView).append(Html.fromHtml("<font size =\"12\" color=\"#ffffff\">${data.desc}</font>"))
        }

        override fun recycled() {
            super.recycled()
        }
    }

    private inner class AD(private var view: View) : Item(view) {

    }

    /**
     * @return 现在是否为收藏
     *      判断之前喜欢状态  true 去除图标着色，删除喜欢
     *      判断之前喜欢状态  false 添加图标着色，添加喜欢
     * */

    fun changeCollection(imageView: ImageView,data: CommentData):Boolean{
        if (!CollectionList.contains(data)){
            imageView.setColorFilter(imageView.context.resources.getColor(R.color.colorAccent))
            CollectionList.add(data)
            return true
        }
        imageView.setColorFilter(Color.WHITE)
        CollectionList.remove(data)
        return false
    }

}