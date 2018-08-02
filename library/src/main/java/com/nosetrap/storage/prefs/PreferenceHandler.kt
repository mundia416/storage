package com.nosetrap.storage.prefs

import android.content.Context

/**
 * class used to handle sharedPreferences
 * simply instantiate it to use
 * @param sharedPreferenceName the name of the apps shared preferences
 */
class PreferenceHandler(context: Context,sharedPreferencesName: String) {

    private val basePrefs = BasePrefs(context,sharedPreferencesName)

    /**
     * get a value of the sharedPreferences
     */
    fun get(key: String, defaultVal: Boolean): Boolean{
        return basePrefs.prefs.getBoolean(key,defaultVal)
    }

    /**
     * get a value of the sharedPreferences
     */
    fun get(key: String, defaultVal: String): String{
        return basePrefs.prefs.getString(key,defaultVal)
    }

    /**
     * get a value of the sharedPreferences
     */
    fun get(key: String, defaultVal: Int): Int{
        return basePrefs.prefs.getInt(key,defaultVal)
    }

    /**
     * get a value of the sharedPreferences
     */
    fun get(key: String, defaultVal: Float): Float{
        return basePrefs.prefs.getFloat(key,defaultVal)
    }

    /**
     * get a value of the sharedPreferences
     */
    fun get(key: String, defaultVal: Long): Long{
        return basePrefs.prefs.getLong(key,defaultVal)
    }

    /**
     * put a value in the shared preferences
     * @param key is the name of the sharedPreference
     */
    fun edit(key: String, value: String){
        basePrefs.editor.putString(key,value).commit()
    }

    /**
     * put a value in the shared preferences
     * @param key is the name of the sharedPreference
     */
    fun edit(key: String, value: Int){
        basePrefs.editor.putInt(key,value).commit()
    }

    /**
     * put a value in the shared preferences
     * @param key is the name of the sharedPreference
     */
    fun edit(key: String, value: Float){
        basePrefs.editor.putFloat(key,value).commit()
    }

    /**
     * put a value in the shared preferences
     * @param key is the name of the sharedPreference
     */
    fun edit(key: String, value: Long){
        basePrefs.editor.putLong(key,value).commit()
    }

    /**
     * put a value in the shared preferences
     * @param key is the name of the sharedPreference
     */
    fun edit(key: String, value: Boolean){
        basePrefs.editor.putBoolean(key,value).commit()
    }
}