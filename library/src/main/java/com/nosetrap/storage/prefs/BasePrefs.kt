package com.nosetrap.storage.prefs

import android.content.Context
import android.content.SharedPreferences
import com.nosetrap.storage.exceptions.InvalidPrefNameException

internal class BasePrefs(context: Context,sharedPreferenceName: String) {

    var prefs: SharedPreferences
    var editor: SharedPreferences.Editor

     init {
        if(sharedPreferenceName.isNotEmpty()) {
             prefs= context.getSharedPreferences(sharedPreferenceName, Context.MODE_PRIVATE)
             editor = prefs.edit()
        }else{
            throw InvalidPrefNameException("empty preference name of length ${sharedPreferenceName.length}")
        }

     }

}