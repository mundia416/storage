package com.nosetrap.storage

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    /**
     *
     */
    data class  Foo(var a:Int,var b:Long,var c: Int)

    /**
     *
     */
    data class Foo2(var foo: Foo,var a: Int)
}
