package com.nosetrap.storage.sql

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.nosetrap.storage.exceptions.SqlDatabaseException

class DatabaseHandler(context: Context, databaseName: String)
    : SQLiteOpenHelper(context,databaseName,null,1) {

    constructor(context: Context) : this(context,"default_sql_database_name")

    private lateinit var database: SQLiteDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    /**
     * create a database table
     * @param stringColumns is a string array of the title of the columns with a type of text ,it is
     *  ignored if it is null
     *  @param intColumns is a string array of the title of the columns with a type of Integer,it is
     *  ignored if it is null
     *  @throws SqlDataBaseException when you try to create a table with no collumns
     */
    fun createTable(tableName:String,stringColumns: Array<String>?,intColumns: Array<String>?){
        //check if there is no column passed
        if((stringColumns == null && intColumns == null) ||
                ((stringColumns?.size ?: 0) == 0 && (intColumns?.size ?: 0) == 0 ) ||
                ((stringColumns?.size ?: 0) == 0 &&  intColumns == null) ||
                ((intColumns?.size ?: 0) == 0 &&  stringColumns == null)){
            throw SqlDatabaseException("attempted to create a table without any columns")
        }

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
        database.execSQL(sqlString)
        database.close()
    }

    /**
     * delete a database table
     */
    fun deleteTable(tableName: String){
        database = writableDatabase
        val sqlString = "DROP TABLE IF EXISTS $tableName"
       database.execSQL(sqlString)
        database.close()
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
              whereClause:String?=null, orderBy: Array<OrderBy>?=null, limit: Int = 0){

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
            if (orderByStringBuilder.isNotEmpty()) {
                //get the limit
                if(limit > 0){
                    orderByStringBuilder.append(" limit $limit")
                }
                orderByString = orderByStringBuilder.toString()
            }
        }

        database = readableDatabase
        val cursor = database.query(tableName,columns,whereClause,null,null,null,orderByString)

        cursor.moveToFirst()
        cursorCallback.onCursorQueried(EasyCursor(cursor))
        cursor.close()
        database.close()
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
        database = writableDatabase
       val count = database.update(tableName,values,whereClause,null)
        database.close()

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
        database = writableDatabase
        val count = database.insert(tableName,null,values)
        database.close()

        return count != -1L

    }

    /**
     * get the number of entries in a table
     */
    fun getCount(tableName: String): Long{
        database = readableDatabase
       val count = DatabaseUtils.queryNumEntries(database,tableName)
        database.close()

        return count
    }

    /**
     * delete row(s) from a database table
     */
    fun removeRows(tableName: String,whereClause: String){
        database = writableDatabase
        database.delete(tableName,whereClause,null)
        database = readableDatabase
    }

    /**
     * deletes all the rows in a table
     */
    fun clearTable(tableName: String){
        database = writableDatabase
        database.delete(tableName,null,null)
        database = readableDatabase
    }
}