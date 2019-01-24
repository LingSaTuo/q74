package com.celivad.bmob

import cn.bmob.v3.BmobUser
import cn.bmob.v3.listener.SaveListener
import rx.Subscription
import java.lang.Exception

class BmobConn : BmobUser() {
    var type = ""
    override fun setUsername(username: String?) {
        super.setUsername(username)
        if (username?.length?:0<5) throw Exception("用户名太短")
    }

    override fun setPassword(password: String?) {
        super.setPassword(password)
        if (password?.length?:0<5) throw Exception("密码太短")
    }

    override fun <T : Any?> login(listener: SaveListener<T>?): Subscription {
        return super.login(listener)
    }

    override fun <T : Any?> signUp(listener: SaveListener<T>?): Subscription {
        return super.signUp(listener)
    }
}