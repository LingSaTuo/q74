package com.celivad.q74.search.fragment.history

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.celivad.q74.R

class RVAdapter : RecyclerView.Adapter<RVAdapter.Item>(){

    private val list = ArrayList<String>()

    private var lis:(String)->Unit = {}

    fun setData(data:ArrayList<String>){
        this.list.clear()
        this.list.addAll(data)
    }

    fun setOnClickListener(lis:(String)->Unit){
        this.lis = lis
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Item {
        return I(LayoutInflater.from(p0.context).inflate(R.layout.item_history,p0,false))
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(p0: Item, p1: Int) {
        p0.bind(this.list[this.list.size-1-p1])
    }

    open class Item(private var view:View):RecyclerView.ViewHolder(view){
        open fun bind(key:String){
            ((view as ConstraintLayout).getChildAt(0) as TextView).text = key
        }

    }
    inner class I(private var view: View) : Item(view){
        override fun bind(key: String) {
            super.bind(key)
            view.setOnClickListener {
                lis.invoke(key)
            }
        }
    }
}