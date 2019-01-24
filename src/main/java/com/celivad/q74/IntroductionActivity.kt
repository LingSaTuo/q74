package com.celivad.q74

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.celivad.app.StateCompatActivity
import com.celivad.q74.data.LoveList
import com.celivad.q74.data.MovieData
import com.celivad.q74.introduction.IntroductionInit
import com.celivad.q74.main.fragment.community.CollectionList
import com.celivad.q74.search.fragment.Bus
import com.celivad.q74.search.fragment.results.ResultBaseData
import kotlinx.android.synthetic.main.activity_movie.*

class IntroductionActivity : StateCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        statusBarColor = Color.TRANSPARENT
        window.decorView.setBackgroundColor(resources.getColor(R.color.background_main))
        appbar.setPadding(0, statusBarHeight, 0, 0)
        UI2Status()
        IntroductionInit(this).init()
    }

    companion object {
        fun startMySelf(context: Context, result: MovieData) {
            val intent = Intent(context, IntroductionActivity::class.java)
            intent.putExtra("baseData", result)
            context.startActivity(intent)
        }
    }


    /**
     * @return 现在是否为喜欢
     *      判断之前喜欢状态  true 去除图标着色，删除喜欢
     *      判断之前喜欢状态  false 添加图标着色，添加喜欢
     * */

    fun changeLove(movieData: MovieData):Boolean{
        if (!LoveList.contains(movieData)){
            love.setColorFilter(resources.getColor(R.color.colorAccent))
            LoveList.add(movieData)
            return true
        }
        love.setColorFilter(Color.WHITE)
        LoveList.remove(movieData)
        return false
    }

    /**
     * @return 现在是否为喜欢
     *      判断之前喜欢状态  true 去除图标着色，删除喜欢
     *      判断之前喜欢状态  false 添加图标着色，添加喜欢
     * */

    fun changeCollection(movieData: MovieData):Boolean{
//        if (!CollectionList.contains(movieData)){
//            collection.setColorFilter(resources.getColor(R.color.colorAccent))
//            CollectionList.add(movieData)
//            return true
//        }
//        collection.setColorFilter(Color.WHITE)
//        CollectionList.remove(movieData)
        return false
    }
    override fun onBackPressed() {
        super.onBackPressed()
        LoveList.save(this)
        CollectionList.save(this)
    }

    fun buildLCA(movieData: MovieData) {
        if (LoveList.contains(movieData)){
            love.setColorFilter(resources.getColor(R.color.colorAccent))
        }
//        if (CollectionList.contains(movieData)){
//            collection.setColorFilter(resources.getColor(R.color.colorAccent))
//        }
    }
}