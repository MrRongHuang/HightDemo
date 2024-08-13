package com.koi.testdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.drake.net.time.Interval
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import java.util.UUID
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var interval: Interval? = null
    private var addInterval: Interval? = null
    private var searchPoiAll = mutableListOf<String>()

    private lateinit var tvShow: TextView
    private var cIndex = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvShow = findViewById<TextView>(R.id.tvShow)
        searchPoiAll = getData()
        showListText(searchPoiAll.size)

        tvShow.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
            Log.e("xxx", "setOnClickListener:")




            Log.e("xxx", "UUID ==  ${UUID.randomUUID()}")

        }


    }

    private fun showListText(size: Int) {
        if (interval != null) {
            interval!!.cancel()
            interval = null
        }
        interval = Interval(
            0, 1, TimeUnit.SECONDS, (size - 1).toLong()
        ).subscribe {
            Log.e("koi", "Processing: ${searchPoiAll[0]}    ====  $it")
            tvShow.text = searchPoiAll[0]
            searchPoiAll.removeAt(0)
            // 模拟在定位过程中  新增了数据
            if (it.toInt() == 4 && cIndex < 5) {
                getAddData(cIndex)
            }
            // 当首次添加的数据遍历完成时，触发后面新增的数据遍历
            if (it.toInt() == 0 && searchPoiAll.isNotEmpty()) {
                tvShow.postDelayed({
                    showListText(searchPoiAll.size)
                }, 1000)
            }
        }.finish {
            Log.e("koi", "All items have been processed and removed.")
        }.start()
    }


    private fun getData(): ArrayList<String> {
        return arrayListOf<String>().apply {
            add("One  111")
            add("One  222")
            add("One  333")
            add("One  444")
            add("One  555")
            add("One  666")
            add("One  777")
        }
    }

    private fun getAddData(position: Int) {
        searchPoiAll.addAll(arrayListOf<String>().apply {
            add("$position  111")
            add("$position  222")
            add("$position  333")
            add("$position  444")
            add("$position  555")
        })
        cIndex++
    }
}