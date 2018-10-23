package com.nosetrap.storage.sql

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.nosetrap.storage.exceptions.SqlDatabaseException

class DatabaseHandler(context: Context, databaseName: String) {

    constructor(context: Context) : this(context,"default_sql_database_name")

    private val databaseExtension = DatabaseHandlerExtension(context, databaseName)

    /**
     * create a database table
     * @param stringColumns is a string array of the title of the columns with a type of text ,it is
     *  ignored if it is null
     *  @param intColumns is a string array of the title of the columns with a type of Integer,it is
     *  ignored if it is null
     * if the stringColumns or intColumns entries have length of zero then the table will be created with
     * only the id collumn
     */
    fun createTable(tableName:String,stringColumns: Array<String>?,intColumns: Array<String>?){
        databaseExtension.createTable(tableName, stringColumns, intColumns)
        databaseExtension.closeConnection()
    }

    /**
     * delete a database table
     */
    fun deleteTable(tableName: String){
        databaseExtension.deleteTable(tableName)
        databaseExtension.closeConnection()
    }


    /**
     * Query the given table
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
    fun query(cursorCallback: CursorCallback, tableName: String, columns: Array<String>?=null,
              whereClause:String?=null, orderBy: Array<OrderBy>?=null, limit: Int = 0,offset: Int = 0){

        val internalCallback = object : CursorCallback{
            override fun onCursorQueried(cursor: EasyCursor) {
                cursorCallback.onCursorQueried(cursor)
                cursor.close()
            }
        }
        databaseExtension.query(internalCallback, tableName, columns, whereClause, orderBy, limit,offset)
        databaseExtension.closeConnection()
    }

    /**
     * Convenience method for updating rows in the database.
     *
     * @param table the table to update in
     * @param values a map from column names to new column values.
     * @param whereClause the optional WHERE clause to apply when updating.
     *            Passing null will update all rows.
     * @return the number of rows affected
     */
    fun update(tableName: String,values: ContentValues,whereClause: String? = null): Int{
       val count = databaseExtension.update(tableName,values,whereClause)
        databaseExtension.closeConnection()

        return count
    }

    /**
     * query everything in a table
     */
    fun getAll(cursorCallback: CursorCallback, tableName: String){
        query(cursorCallback,tableName,null,null,null )
    }

    /**
     * insert a row into a table
     *
     * @return true if successfully inserted and false if an error occured
     */
    fun insert(tableName: String, values: ContentValues): Boolean{
        val success = databaseExtension.insert(tableName, values)
        databaseExtension.close()
        return success
    }

    /**
     * get the number of entries in a table
     */
    fun getCount(tableName: String): Long{
       val count = databaseExtension.getCount(tableName)
       databaseExtension.closeConnection()
        return count
    }

    /**
     * delete row(s) from a database table
     *      * @return the number of values that have been removed from the table

     */
    fun removeRows(tableName: String,whereClause: String):Int{
       val count = databaseExtension.removeRows(tableName,whereClause)
        databaseExtension.closeConnection()
        return count
    }

    /**
     * deletes all the rows in a table
     *      * @return the number of values that have been removed from the table
     */
    fun clearTable(tableName: String): Int{
       val count = databaseExtension.clearTable(tableName)
        databaseExtension.closeConnection()
        return count
    }
}