package com.nosetrap.storage.pojo

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.google.gson.Gson
import com.nosetrap.storage.sql.CursorCallback
import com.nosetrap.storage.sql.DatabaseHandler
import com.nosetrap.storage.sql.DatabaseHandlerExtension
import com.nosetrap.storage.sql.EasyCursor
import java.lang.reflect.Type

/**
 * an extension class to add more functionality to the pojo class
 * @Warning Use wisely as improper use could lead to data leaks
 */
class PojoExtension (private val context: Context, private val tableName: String = "pojo_objects_table_name") {

    private val databaseHandlerExtension = DatabaseHandlerExtension(context, "pojo_objects_database_name")

    /**
     * the column which contains the content of a pojo
     */
    private val colPojoContent = "pojo_content"

    /**
     * the column which contains the key for the pojo
     */
    private val colPojoKey = "pojo_key"

    /**
     * variable that returns the number of pojo entries in the tablename, the database connection is not closed after its done
     */
    var count: Long = 0
        private set
        get() {
            return databaseHandlerExtension.getCount(tableName)
        }

    init {
        databaseHandlerExtension.createTable(tableName, arrayOf(colPojoContent, colPojoKey), null)
    }

    /**
     * closes the connection to the database
     */
    fun closeConnection(){
        databaseHandlerExtension.closeConnection()
    }

    /**
     * gets rid of all Pojo objects saved in the sql Database
     * the database connection is not closed after its done
     */
    fun releaseAll() {
        databaseHandlerExtension.clearTable(tableName)
    }

    /**
     * get rid of a pojo which has the specified key
     * the database connection is not closed after its done
     */
    fun delete(key: String) {
        databaseHandlerExtension.removeRows(tableName, "$colPojoKey = '$key'")
    }

    /**
     * update the details of a pojo
     * the database connection is not closed after its done
     */
    fun update(key: String, pojoObject: Any) {
        val json = Gson().toJson(pojoObject)
        val values = ContentValues()
        values.put(colPojoKey, key)
        values.put(colPojoContent, json)
        databaseHandlerExtension.update(tableName, values, "$colPojoKey = '$key'")
    }

    /**
     * insert a pojo object into the database,the database connection is not closed after its done
     * @param pojoObject the Plain Old Java Object to insert into the sql database
     * @param key is the string key which is used to retrieve the pojo from the database
     * throws IllegalStateException if the key already exists in the database
     *
     */
    fun insert(key: String, pojoObject: Any) {
        if (!isExist(key)) {
            //the key does not exist in the database so create a new entry
            val json = Gson().toJson(pojoObject)
            val values = ContentValues()
            values.put(colPojoKey, key)
            values.put(colPojoContent, json)
            databaseHandlerExtension.insert(tableName, values)
        } else {
            //the key already exists so throw an illegalStateException
            throw IllegalStateException("key already exists in database, use a different key or use insertOrUpdate() method")
        }
    }

    /**
     * inserts into the database but if the key already exists then it will simply update the pojo which
     * is in the database assigned to the key,
     * the database connection is not closed after its done
     */
    fun insertOrUpdate(key: String, pojoObject: Any) {
        try {
            insert(key, pojoObject)
        } catch (e: IllegalStateException) {
            update(key, pojoObject)
        }
    }

    /**
     * checks whether there is a pojo that exists in the database with the specified key
     * the database connection is not closed after its done
     */
    fun isExist(key: String): Boolean {
        var isExist = false
        databaseHandlerExtension.query(object : CursorCallback {
            override fun onCursorQueried(cursor: EasyCursor) {
                if (cursor.getCount() > 0) {
                    isExist = true
                }
                cursor.close()
            }
        }, tableName, arrayOf(BaseColumns._ID),
                "$colPojoKey = '$key'", null, 1)

        return isExist
    }


    /**
     * the database connection is not closed after its done
     *
     * throws NullPointerException if the object does not exist in the database
     * make sure that the Pojo has a constructor without parameters
     *
     * throws NullPointerException if the pojo does not exist in the database with the specified key
     */
    fun <T> get(key: String, type: Type): T {
        var jsonObject: T? = null

        databaseHandlerExtension.query(object : CursorCallback {
            override fun onCursorQueried(cursor: EasyCursor) {
                var json: String? = null
                try {
                    json = cursor.getString(colPojoContent)
                } catch (e: Exception) {
                }
                //throw a NPE if json is null which means the pojo was never in the database
                if (json == null) {
                    throw  NullPointerException("no Pojo exists in the database with the specified key")
                }

                jsonObject = Gson().fromJson(json, type)
                cursor.close()
            }
        }, tableName, arrayOf(colPojoContent), "$colPojoKey = '$key'")

        return jsonObject!!
    }
}