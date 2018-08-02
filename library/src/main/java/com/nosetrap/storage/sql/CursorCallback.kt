package com.nosetrap.storage.sql

import android.database.Cursor

interface CursorCallback {
    /**
     * is called when a cursor has been retrieved
     * @NOTE do not close the cursor,the cursor is already automatically closed
     */
    fun onCursorQueried(cursor: Cursor)
}