package com.nosetrap.storage.storage

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.util.TypedValue
import com.google.gson.Gson
import com.snatik.storage.helpers.SizeUnit
import java.io.File
import java.lang.reflect.Type

/**
 * storage object that lets you read and write to external/internal storage
 */
open class Storage(context: Context) {
    companion object {
        /**
         * check whether the external storage can be written to (if it is mounted)
         */
        fun isExternalWritable(): Boolean {
            return com.snatik.storage.Storage.isExternalWritable()
        }
    }

    protected val storage = com.snatik.storage.Storage(context)

    val externalStorageDir = storage.externalStorageDirectory
    val cacheDir = storage.internalCacheDirectory
    val internalStorageDir = storage.internalFilesDirectory

    /**
     * create a directory
     */
    fun createDir(path: String): Boolean {
        return storage.createDirectory(path)
    }

    /**
     * create a directory
     * @param override determines whether it should override the current directory if it exists
     */
    fun createDir(path: String, override: Boolean): Boolean {
        return storage.createDirectory(path, override)
    }

    /**
     * delete a directory
     */
    fun deleteDir(path: String): Boolean {
        return storage.deleteDirectory(path)
    }

    /**
     * delete a file
     */
    fun deleteFile(path: String): Boolean {
        return storage.deleteFile(path)
    }

    /**
     * check if a directory exists
     */
    fun isDirExist(path: String): Boolean {
        return storage.isDirectoryExists(path)
    }

    /**
     * check if a file exists
     */
    fun isFileExist(path: String): Boolean {
        return storage.isFileExist(path)
    }

    /**
     *
     */
    fun createFile(path: String, content: ByteArray): Boolean {
        return storage.createFile(path, content)
    }

    /**
     *
     */
    fun createFile(path: String, content: Bitmap): Boolean {
        return storage.createFile(path, content)
    }

    /**
     *
     */
    fun createFile(path: String, content: String): Boolean {
        return storage.createFile(path, content)
    }

    fun createFile(path: String, dataObject: Any): Boolean {
        val jsonString = Gson().toJson(dataObject)
        return createFile(path, jsonString)
    }

    fun readFile(path: String): ByteArray {
        return storage.readFile(path)
    }

    fun readTextFile(path: String): String {
        return storage.readTextFile(path)
    }

    fun <T> readFile(path: String, type: Type): T {
        val jsonString = readTextFile(path)
        return Gson().fromJson(jsonString, type)
    }

    fun appendFile(path: String, content: String) {
        appendFile(path, content.toByteArray())
    }

    fun appendFile(path: String, bytes: ByteArray) {
        storage.appendFile(path, bytes)
    }

    fun getFiles(dir: String): List<File> {
        return getFiles(dir, null)
    }

    fun getFiles(dir: String, matchRegex: String?): List<File> {
        return storage.getFiles(dir, matchRegex)
    }

    fun getFile(path: String): File {
        return storage.getFile(path)
    }

    fun rename(fromPath: String, toPath: String): Boolean {
        return storage.rename(fromPath, toPath)
    }

    fun getSize(file: File, unit: SizeUnit): Double {
        return storage.getSize(file, unit)
    }

    fun getReadableSize(file: File): String {
        return storage.getReadableSize(file)
    }

    fun getFreeSpace(dir: String, sizeUnit: SizeUnit): Long {
        return storage.getFreeSpace(dir, sizeUnit)
    }

    fun getUsedSpace(dir: String, sizeUnit: SizeUnit): Long {
        return storage.getUsedSpace(dir, sizeUnit)
    }

}