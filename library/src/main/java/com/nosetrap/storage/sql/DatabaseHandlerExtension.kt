package com.nosetrap.storage.sql

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/**
 * an extension class to add more functionality to the pojo class
 * @Warning Use wisely as improper use could lead to data leaks
 */
class DatabaseHandlerExtension(context: Context, databaseName: String) : SQLiteOpenHelper(context,databaseName,null,1) {

    constructor(context: Context) : this(context,"default_sql_database_name")


    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    /**
     * database actions are performed on this object so you are able to assign your own SqliteDatabase
     * object so that actions performed can be on it,
     * Note that the connection is not closed after an action is performed on it so dont forget to close
     * it after you are done
     */
    var database: SQLiteDatabase? = null

    /**
     * create a database table without closing the database connection after the table is created
     * @param stringColumns is a string array of the title of the columns with a type of text ,it is
     *  ignored if it is null
     *  @param intColumns is a string array of the title of the columns with a type of Integer,it is
     *  ignored if it is null
     * if the stringColumns or intColumns entries have length of zero then the table will be created with
     * only the id collumn
     */
    fun createTable(tableName:String,stringColumns: Array<String>?,intColumns: Array<String>?){
        //check if there is no column passed
        /* if((stringColumns == null && intColumns == null) ||
                 ((stringColumns?.size ?: 0) == 0 && (intColumns?.size ?: 0) == 0 ) ||
                 ((stringColumns?.size ?: 0) == 0 &&  intColumns == null) ||
                 ((intColumns?.size ?: 0) == 0 &&  stringColumns == null)){
             throw SqlDatabaseException("attempted to create a table without any columns")
         }*/

        //create the columns string

        val colStringBuilder = StringBuilder()

        if(stringColumns != null){
            for (col in stringColumns){
                colStringBuilder.append(", $col TEXT")
            }
        }

        if(intColumns != null){
            for (col in intColumns){
                colStringBuilder.append(", $col INTEGER")
            }
        }

        //creating the table
        val sqlString = "CREATE TABLE IF NOT EXISTS $tableName(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT" +
                "${colStringBuilder.toString()} )"

        database = writableDatabase
        database?.execSQL(sqlString)
    }

    /**
     * closes the database connection
     */
    fun closeConnection(){
        database?.close()
    }

    /**
     * delete a database table without closing the connection after its done
     */
    fun deleteTable(tableName: String){
        database = writableDatabase
        val sqlString = "DROP TABLE IF EXISTS $tableName"
        database?.execSQL(sqlString)
    }

    /**
     * Query the given table without closing the database connection after its done,
     * it also doesn't close the cursor as well
     *
     * @param table The table name to compile the query against.
     * @param columns A list of which columns to return. Passing null will
     *            return all columns, which is discouraged to prevent reading
     *            data from storage that isn't going to be used.
     * @param whereClause A filter declaring which rows to return, formatted as an
     *            SQL WHERE clause (excluding the WHERE itself). Passing null
     *            will return all rows for the given table.
     *@param cursorCallback the callback to which the cursor is passed to on a successful query
     * @param orderBy How to order the rows,
     * @param limit get a specified amount of rows. if 0 it will return all the found rows
     */
    fun query(callback: (cursor: EasyCursor) -> Unit,tableName: String, columns: Array<String>?=null,
              whereClause:String?=null, orderBy: Array<OrderBy>?=null,limit: Int = 0,offset: Int = 0){

        //get the orderby string
        var orderByString:String? = null
        if(orderBy != null) {
            val orderByStringBuilder = StringBuilder()
            for (i in 0..(orderBy.size - 1)) {
                if (i == 0) {
                    orderByStringBuilder.append("${orderBy[i].column} ${OrderBy.convertMethodToString(orderBy[i].method)}")
                } else {
                    orderByStringBuilder.append(",${orderBy[i].column} ${OrderBy.convertMethodToString(orderBy[i].method)}")
                }
            }

            if(orderByStringBuilder.isNotBlank()){
                orderByString = orderByStringBuilder.toString()
            }
        }

        databaseReadable()
        val cursor = if(limit == 0) {
            database?.query(tableName, columns, whereClause, null, null,
                    null, orderByString)
        }else{
            database?.query(tableName, columns, whereClause, null, null,
                    null, orderByString,"$offset,$limit")
        }

        cursor?.moveToFirst()
        callback(EasyCursor(cursor!!))
    }

    /**
     * make the database a writable database
     * this opens a connection
     * @Warning could leak data if not closed
     */
    fun databaseWritable(){
        database = writableDatabase
    }

    /**
     * make the database a readable database
     * this opens a connection
     * @Warning could leak data if not closed
     */
    fun databaseReadable(){
        database = readableDatabase
    }

    /**
     * Convenience method for updating rows in the database.the database connection is left open
     *
     * @param table the table to update in
     * @param values a map from column names to new column values.
     * @param whereClause the optional WHERE clause to apply when updating.
     *            Passing null will update all rows.
     * @return the number of rows affected
     */
    fun update(tableName: String, values: ContentValues, whereClause: String? = null): Int{
        database = writableDatabase
        val count = database?.update(tableName,values,whereClause,null)

        return count!!
    }

    /**
     * query everything in a table, the connection is not closed after its done
     */
    fun getAll(callback: (cursor: EasyCursor) -> Unit, tableName: String){
        query(callback,tableName,null,null,null )
    }

    /**
     * insert a row into a table,the connection is not closed after its done
     *
     * @return true if successfully inserted and false if an error occured
     */
    fun insert(tableName: String, values: ContentValues): Boolean{
        database = writableDatabase
        val count = database?.insert(tableName,null,values)

        return count != -1L
    }

    /**
     * get the number of entries in a table, the connection is not closed after its done
     */
    fun getCount(tableName: String): Long{
        database = readableDatabase
        val count = DatabaseUtils.queryNumEntries(database,tableName)
        return count
    }

    /**
     *
     */
    fun execSQL(sql: String){
        database?.execSQL(sql)
    }

    /**
     * delete row(s) from a database table, the connection is not closed after its done
     *      * @return the number of values that have been removed from the table

     */
    fun removeRows(tableName: String,whereClause: String):Int{
        database = writableDatabase
        return database?.delete(tableName,whereClause,null)!!
    }

    /**
     * deletes all the rows in a table
     * @return the number of values that have been removed from the table
     */
    fun clearTable(tableName: String): Int {
        database = writableDatabase
        return database?.delete(tableName,null,null)!!
    }
}