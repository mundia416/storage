package com.nosetrap.storage.prefs

import android.content.Context
import android.content.SharedPreferences

 internal class BasePrefs(protected var context: Context,sharedPreferenceName: String) {

     var prefs: SharedPreferences
     var editor: SharedPreferences.Editor

    init {
        prefs = context.getSharedPreferences(sharedPreferenceName, Context.MODE_PRIVATE)
        editor = prefs.edit()
    }
}