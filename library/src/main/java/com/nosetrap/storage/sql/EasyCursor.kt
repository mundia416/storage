package com.nosetrap.storage.sql

import android.database.Cursor

/**
 * a class which simplifies the android cursor class.
 * @WARNING this class should only ever be instantiated in this library, instantiating
 * it outside of this library could lead to unintended memory leaks
 */
class EasyCursor(val cursor: Cursor) {

    fun moveToNext(){
        cursor.moveToNext()
    }

    /**
     * Move the cursor to an absolute position. The valid
     * range of values is -1 &lt;= position &lt;= count.
     *
     *
     * This method will return true if the request destination was reachable,
     * otherwise, it returns false.
     *
     * @param position the zero-based position to move to.
     * @return whether the requested move fully succeeded.
     */
    fun moveToPosition(position: Int): Boolean {
       return cursor.moveToPosition(position)
    }

    /**
     * Returns the value of the requested column as a long.
     *
     *
     * The result and whether this method throws an exception when the
     * column value is null, the column type is not an integral type, or the
     * integer value is outside the range [`Long.MIN_VALUE`,
     * `Long.MAX_VALUE`] is implementation-defined.
     *
     * @param column the name of the column to get the long from
     * @return the value of that column as a long.
     */
     fun getLong(column :String): Long {
       return cursor.getLong(cursor.getColumnIndex(column))
    }

    /**
     * Move the cursor to the first row.
     *
     *
     * This method will return false if the cursor is empty.
     *
     * @return whether the move succeeded.
     */
     fun moveToFirst(): Boolean {
return cursor!!.moveToFirst()
    }

    /**
     * Returns the value of the requested column as a float.
     *
     *
     * The result and whether this method throws an exception when the
     * column value is null, the column type is not a floating-point type, or the
     * floating-point value is not representable as a `float` value is
     * implementation-defined.
     *
     * @param column the name of the column to get the float from
     * @return the value of that column as a float.
     */
     fun getFloat(column: String): Float {
return cursor!!.getFloat(cursor!!.getColumnIndex(column))
    }

    /**
     * Move the cursor to the previous row.
     *
     *
     * This method will return false if the cursor is already before the
     * first entry in the result set.
     *
     * @return whether the move succeeded.
     */
     fun moveToPrevious(): Boolean {
return cursor!!.moveToPrevious()    }

    /**
     * Returns the value of the requested column as a double.
     *
     *
     * The result and whether this method throws an exception when the
     * column value is null, the column type is not a floating-point type, or the
     * floating-point value is not representable as a `double` value is
     * implementation-defined.
     *
     * @param column the name of the column to get the double from
     * @return the value of that column as a double.
     */
     fun getDouble(column: String): Double {
 return cursor!!.getDouble(cursor!!.getColumnIndex(column))   }

    /**
     * Returns the numbers of rows in the cursor.
     *
     * @return the number of rows in the cursor.
     */
     fun getCount(): Int {
return cursor!!.count
    }

    /**
     * Returns whether the cursor is pointing to the first row.
     *
     * @return whether the cursor is pointing at the first entry.
     */
     fun isFirst(): Boolean {
return cursor!!.isFirst   }

    /**
     * Returns `true` if the value in the indicated column is null.
     *
     * @param column the name of the column to check whether its value is null
     * @return whether the column value is null.
     */
     fun isNull(column: String): Boolean {
        return  cursor!!.isNull(cursor!!.getColumnIndex(column))
    }

    /**
     * Returns a string array holding the names of all of the columns in the
     * result set in the order in which they were listed in the result.
     *
     * @return the names of the columns returned in this query.
     */
     fun getColumnNames(): Array<String> {
return cursor!!.columnNames   }

    /**
     * Returns the value of the requested column as an int.
     *
     *
     * The result and whether this method throws an exception when the
     * column value is null, the column type is not an integral type, or the
     * integer value is outside the range [`Integer.MIN_VALUE`,
     * `Integer.MAX_VALUE`] is implementation-defined.
     *
     * @param column the name of the column to get the int from
     * @return the value of that column as an int.
     */
     fun getInt(column: String): Int {
return cursor!!.getInt(cursor!!.getColumnIndex(column))
    }

    /**
     * Returns whether the cursor is pointing to the last row.
     *
     * @return whether the cursor is pointing at the last entry.
     */
     fun isLast(): Boolean {
return cursor!!.isLast   }

    /**
     * Returns the current position of the cursor in the row set.
     * The value is zero-based. When the row set is first returned the cursor
     * will be at positon -1, which is before the first row. After the
     * last row is returned another call to next() will leave the cursor past
     * the last entry, at a position of count().
     *
     * @return the current cursor position.
     */
     fun getPosition(): Int {
return cursor!!.position   }

    /**
     * Move the cursor to the last row.
     *
     *
     * This method will return false if the cursor is empty.
     *
     * @return whether the move succeeded.
     */
     fun moveToLast(): Boolean {
return cursor!!.moveToLast()   }

    /**
     * Returns the value of the requested column as a String.
     *
     *
     * The result and whether this method throws an exception when the
     * column value is null or the column type is not a string type is
     * implementation-defined.
     *
     * @param column the name of the column to get the string from
     * @return the value of that column as a String.
     */
     fun getString(column: String): String {
return cursor!!.getString(cursor!!.getColumnIndex(column))   }

    /**
     * Move the cursor by a relative amount, forward or backward, from the
     * current position. Positive offsets move forwards, negative offsets move
     * backwards. If the final position is outside of the bounds of the result
     * set then the resultant position will be pinned to -1 or count() depending
     * on whether the value is off the front or end of the set, respectively.
     *
     *
     * This method will return true if the requested destination was
     * reachable, otherwise, it returns false. For example, if the cursor is at
     * currently on the second entry in the result set and move(-5) is called,
     * the position will be pinned at -1, and false will be returned.
     *
     * @param offset the offset to be applied from the current position.
     * @return whether the requested move fully succeeded.
     */
     fun move(offset: Int): Boolean {
  return cursor!!.move(offset)   }

    /**
     * Return total number of columns
     * @return number of columns
     */
     fun getColumnCount(): Int {
return cursor!!.columnCount   }


}