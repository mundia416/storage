package com.nosetrap.storage.sql

import android.database.Cursor

/**
 * is the interface which contains the code to be executed on each item in the cursor when it is iterated
 * in the EasyCursor
 */
interface IterateListener {
    /**
     * should contain code to be executed on each item in the cursor
     */
    fun onNext(cursor: EasyCursor)
}