package com.yotdark.example_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.download_1)
        add(R.drawable.download_2)
        add(R.drawable.download_3)
        add(R.drawable.download_4)
    }

    private val textList = mutableListOf<String>().apply {
        add("대한민국")
        add("미국")
        add("영국")
        add("중국")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainInitViewPager2()

    }

    private fun mainInitViewPager2(){
        content_text.text = "대한민국"
        viewPager.apply {
            clipToPadding= false
            clipChildren= false
            offscreenPageLimit = 1
            adapter = ViewPager2Adepter(this@MainActivity, imageList)
        }
        viewPager.setPageTransformer(MarginPageTransformer(100))
        viewPager.setPadding(200,0,200,0)
        mainViewChangeEvent()
    }

    private fun mainViewChangeEvent(){
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                content_text.text = textList[position]
            }
        })
    }
}