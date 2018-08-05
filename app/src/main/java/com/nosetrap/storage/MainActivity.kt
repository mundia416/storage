package com.nosetrap.storage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nosetrap.storage.sql.DatabaseHandler
import com.nosetrap.storage.sql.OrderBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val d = DatabaseHandler(this)
        val tableName = "dfdf"

        create.setOnClickListener {
            d.createTable(tableName, arrayOf("fsfd", "df"), null)
            Toast.makeText(this,"created" +
                    "",Toast.LENGTH_SHORT).show()

        }

        delete.setOnClickListener {
            d.deleteTable(tableName)
            Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show()
        }



    }
}
