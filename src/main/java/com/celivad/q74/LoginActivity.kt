package com.celivad.q74

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.celivad.app.StateCompatActivity
import com.celivad.bmob.BmobConn
import com.celivad.io.CFile
import com.celivad.io.CIO
import com.celivad.q74.introduction.LegalCheck
import com.celivad.q74.main.fragment.my.User
import kotlinx.android.synthetic.main.activity_sign.*

class LoginActivity : StateCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        window.decorView.setBackgroundColor(resources.getColor(R.color.background_main))
        statusBarColor = Color.TRANSPARENT
        UI2Status()
        try {
            val str = CFile(this@LoginActivity.filesDir.absolutePath + "/sl").readAll()
            val all = String(str)
            user_name.text = all.split("\n")[0]
            user_password.text = all.split("\n")[1]
        }catch (e:Throwable){}
        login.setOnClickListener {
            try {
                login(user_name.text, user_password.text)
            }catch (e:Throwable){
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
        sign.setOnClickListener {
            try {
                sign(user_name.text, user_password.text)
            }catch (e:Throwable){
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun login(username: String, passworld: String) {
        val user = BmobConn()
        user.username = username
        user.setPassword(passworld)
        user.login(object : SaveListener<BmobConn>() {
            override fun done(p0: BmobConn?, p1: BmobException?) {
                User.user = p0
                if (p0 != null) {
                    Toast.makeText(this@LoginActivity, "Hello ${LegalCheck.getType()}", Toast.LENGTH_LONG).show()
                    CFile(this@LoginActivity.filesDir.absolutePath+"/sl").coveredWrite("$username\n$passworld".toByteArray())
                    finish()
                }
                if (p1 != null) {
                    Toast.makeText(this@LoginActivity, "登陆失败"+p1.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    private fun sign(username: String, passworld: String) {
        val user = BmobConn()
        user.username = username
        user.setPassword(passworld)
        user.signUp(object : SaveListener<BmobConn>() {
            override fun done(p0: BmobConn?, p1: BmobException?) {
                User.user = p0
                if (p0 != null) {
                    Toast.makeText(this@LoginActivity, "Hello ${LegalCheck.getType()}", Toast.LENGTH_LONG).show()
                    CFile(this@LoginActivity.filesDir.absolutePath+"/sl").coveredWrite("$username\n$passworld".toByteArray())
                    finish()
                }
                if (p1 != null) {
                    Toast.makeText(this@LoginActivity, "注册失败"+p1.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}