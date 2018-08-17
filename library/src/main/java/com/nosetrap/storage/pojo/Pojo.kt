package com.nosetrap.storage.pojo

import android.content.Context
import java.lang.reflect.Type

/**
 * stores plain pojo objects into an sql database where they can be retrieved
 */
class Pojo(context: Context,private val tableName: String = "pojo_objects_table_name") {

    private val pojoExtension = PojoExtension(context, "pojo_objects_database_name")



    /**
     * variable that returns the number of pojo entries in the tablename
     */
    var count: Long = 0
        private set
        get() {
            val count = pojoExtension.count
            pojoExtension.closeConnection()
            return count
        }


    /**
     * gets rid of all Pojo objects saved in the sql Database
     */
    fun releaseAll() {
        pojoExtension.releaseAll()
        pojoExtension.closeConnection()
    }

    /**
     * get rid of a pojo which has the specified key
     */
    fun delete(key: String) {
        pojoExtension.delete(key)
        pojoExtension.closeConnection()
    }

    /**
     * update the details of a pojo
     */
    fun update(key: String, pojoObject: Any) {
        pojoExtension.update(key, pojoObject)
        pojoExtension.closeConnection()
    }

    /**
     * insert a pojo object into the database
     * @param pojoObject the Plain Old Java Object to insert into the sql database
     * @param key is the string key which is used to retrieve the pojo from the database
     * throws IllegalStateException if the key already exists in the database
     */
    fun insert(key: String, pojoObject: Any) {
        pojoExtension.insert(key,pojoObject)
        pojoExtension.closeConnection()
    }

    /**
     * inserts into the database but if the key already exists then it will simply update the pojo which
     * is in the database assigned to the key
     */
    fun insertOrUpdate(key: String, pojoObject: Any) {
        pojoExtension.insertOrUpdate(key, pojoObject)
        pojoExtension.closeConnection()
    }

    /**
     * checks whether there is a pojo that exists in the database with the specified key
     */
    fun isExist(key: String): Boolean {
        var isExist = pojoExtension.isExist(key)
        pojoExtension.closeConnection()
        return isExist
    }


    /**
     *
     * throws NullPointerException if the object does not exist in the database
     * make sure that the Pojo has a constructor without parameters
     *
     * throws NullPointerException if the pojo does not exist in the database with the specified key
     */
    fun <T> get(key: String, type: Type): T {
        var jsonObject: T = pojoExtension.get(key, type)
        pojoExtension.closeConnection()
        return jsonObject!!
    }
}