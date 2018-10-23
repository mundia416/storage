package com.nosetrap.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
