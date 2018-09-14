package com.nosetrap.storage.storage

import android.content.Context

/**
 * an extension class that adds more functionality to the Storage object
 */
class StorageExtension(context: Context) : Storage(context){
    /**
     * get the storage object from the natik android storage library
     * which gives you more methods and functionality
     */
    fun getStorageObject(): com.snatik.storage.Storage{
        return storage
    }
}