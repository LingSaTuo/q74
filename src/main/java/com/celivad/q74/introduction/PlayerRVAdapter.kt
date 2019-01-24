package com.celivad.q74.introduction

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.celivad.q74.R
import kotlinx.android.synthetic.main.item_player.view.*

class PlayerRVAdapter : RecyclerView.Adapter<PlayerRVAdapter.PlayerItem>(){
    private val players = ArrayList<String>()

    fun setPlayers(players : ArrayList<String>){
        this.players.clear()
        this.players.addAll(players)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerItem {
        return PlayerItem(LayoutInflater.from(p0.context).inflate(R.layout.item_player,null, false))
    }

    override fun getItemCount(): Int {
        return this.players.size
    }

    override fun onBindViewHolder(p0: PlayerItem, p1: Int) {
        p0.bind(this.players[p1])
    }

    open class PlayerItem(private var view: View):RecyclerView.ViewHolder(view){
        open fun bind(name:String){
            view.player_name.text = name
        }
    }
}