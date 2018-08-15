package com.nosetrap.storage

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nosetrap.storage.pojo.Pojo
import com.nosetrap.storage.sql.DatabaseHandler
import com.nosetrap.storage.sql.OrderBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val d = DatabaseHandler(this)
        val tableName = "dfdf"
        val colA = "a"

        create.setOnClickListener {
            d.createTable(tableName, arrayOf(colA), null)
            Toast.makeText(this,"created" +
                    "",Toast.LENGTH_SHORT).show()

        }

        delete.setOnClickListener {
            d.deleteTable(tableName)
            Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show()
        }

        insert.setOnClickListener {
            val values = ContentValues()
            values.put(colA,"b")
            d.insert(tableName,values)
        }

        clear.setOnClickListener {
            d.clearTable(tableName)
        }

        val pojo = Pojo(this)
        val key = "hey_hey_hey"
        val key2 = "hey_2"

        val foo = Foo(500,111,666)
        val foo2 = Foo2(foo,444)

        savePojo.setOnClickListener {
            try {
                pojo.insert(key, foo)
                pojo.insert(key2,foo2)
            }catch (e:Exception){
                e.printStackTrace()
                pojo.insertOrUpdate(key, foo)
                pojo.insertOrUpdate(key2,foo2)
            }
        }
        getPojo.setOnClickListener {
            val foo1: Foo = pojo.get(key,Foo::class.java)
            pojo.get(key2,Array<Foo2>::class.java)
            Log.d("fdf",foo1.toString())
            Log.d("fdf",foo1.toString())
        }

        updatePojo.setOnClickListener {
            foo.a++
            pojo.update(key,foo)
            foo2.foo = foo
            pojo.update(key2,foo2)
        }


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
