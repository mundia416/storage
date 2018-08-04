package com.nosetrap.storage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nosetrap.storage.sql.DatabaseHandler
import com.nosetrap.storage.sql.OrderBy

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val d = DatabaseHandler(this)
        d.createTable("dfdf", arrayOf("fsfd","df"),null)
        d.getCount("dfdf")



    }
}
