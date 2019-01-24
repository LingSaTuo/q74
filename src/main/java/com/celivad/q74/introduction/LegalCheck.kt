package com.celivad.q74.introduction

import android.annotation.SuppressLint
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import com.celivad.bmob.BmobConn
import com.celivad.q74.R
import com.celivad.q74.main.fragment.my.User

class LegalCheck(private var introductionData: IntroductionData, private var context: Context) {


    private val view = LayoutInflater.from(context).inflate(R.layout.warning_introduction, null, false)


    companion object {
        private val hs = HashMap<String, String>()
        init {
            hs["su"] = "管理员"
            hs["inspector"] = "检查员"
            hs["vip"] = "会员"
        }
        fun getType():String{
            println(User.user?.type)
            if (hs[User.user?.type]==null) return User.user?.username?:""
            return "${hs[User.user?.type]} ${User.user?.username}"
        }
        fun isSu() = User.user?.type == "su"

        fun isVip() = User.user?.type == "vip"

        fun isInspector() = User.user?.type == "inspector"

        fun isL():Boolean{
            return isSu() || isVip() || isInspector()
        }
    }



    @SuppressLint("SetTextI18n")
    fun isLegal(): Boolean {
        if (isL()) return true
        if (introductionData.isLegal && introductionData.bmobLegal.legal == null) return true
        val dia = AlertDialog.Builder(context)
            .setView(view)
            .show()
        dia.window?.setGravity(Gravity.BOTTOM)
        dia.window?.setWindowAnimations(R.style.boytom_menu)
        dia.window?.setBackgroundDrawableResource(R.drawable.shape_card)
        if (introductionData.isLegal && introductionData.bmobLegal.legal != null) {
            ((view as ConstraintLayout).getChildAt(2) as TextView).text = "该资源已被管理员封禁\n封禁原因 :\n" +
                    introductionData.bmobLegal.legal?.msg
        }
        return false
    }
}