package com.nosetrap.storage.storage

import android.content.Context
import android.os.Build
import android.os.Environment
import android.support.annotation.RequiresApi

/**
 * an extension class that adds more functionality to the Storage object
 */
class StorageExtension(private val context: Context) : Storage(context) {
    /**
     * get the storage object from the natik android storage library
     * which gives you more methods and functionality
     */
    fun getStorageObject(): com.snatik.storage.Storage {
        return storage
    }

    /**
     * Return the path of /system.
     *
     * @return the path of /system
     */
    fun getRootPath(): String {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * Return the path of /data.
     *
     * @return the path of /data
     */
    fun getDataPath(): String {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    /**
     * Return the path of /cache.
     *
     * @return the path of /cache
     */
    public fun getDownloadCachePath(): String {
        return Environment.getDownloadCacheDirectory().getAbsolutePath();
    }

    /**
     * Return the path of /data/data/package.
     *
     * @return the path of /data/data/package
     */
    public fun getInternalAppDataPath(): String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return context.getApplicationInfo().dataDir;
        }
        return context.getDataDir().getAbsolutePath();
    }

    /**
     * Return the path of /data/data/package/code_cache.
     *
     * @return the path of /data/data/package/code_cache
     */
    public fun getInternalAppCodeCacheDir(): String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return context.getApplicationInfo().dataDir + "/code_cache";
        }
        return context.getCodeCacheDir().getAbsolutePath();
    }

    /**
     * Return the path of /data/data/package/cache.
     *
     * @return the path of /data/data/package/cache
     */
    public fun getInternalAppCachePath(): String {
        return context.getCacheDir().getAbsolutePath();
    }

    /**
     * Return the path of /data/data/package/databases.
     *
     * @return the path of /data/data/package/databases
     */
    public fun getInternalAppDbsPath(): String {
        return context.getApplicationInfo().dataDir + "/databases";
    }

    /**
     * Return the path of /data/data/package/databases/name.
     *
     * @param name The name of database.
     * @return the path of /data/data/package/databases/name
     */
    public fun getInternalAppDbPath(name: String): String {
        return context.getDatabasePath(name).getAbsolutePath();
    }

    /**
     * Return the path of /data/data/package/files.
     *
     * @return the path of /data/data/package/files
     */
    public fun getInternalAppFilesPath(): String {
        return context.getFilesDir().getAbsolutePath();
    }

    /**
     * Return the path of /data/data/package/shared_prefs.
     *
     * @return the path of /data/data/package/shared_prefs
     */
    public fun getInternalAppSpPath(): String {
        return context.getApplicationInfo().dataDir + "shared_prefs";
    }

    /**
     * Return the path of /data/data/package/no_backup.
     *
     * @return the path of /data/data/package/no_backup
     */
    public fun getInternalAppNoBackupFilesPath(): String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return context.getApplicationInfo().dataDir + "no_backup";
        }
        return context.getNoBackupFilesDir().getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0.
     *
     * @return the path of /storage/emulated/0
     */
    public fun getExternalStoragePath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Music.
     *
     * @return the path of /storage/emulated/0/Music
     */
    public fun getExternalMusicPath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Podcasts.
     *
     * @return the path of /storage/emulated/0/Podcasts
     */
    public fun getExternalPodcastsPath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Ringtones.
     *
     * @return the path of /storage/emulated/0/Ringtones
     */
    public fun getExternalRingtonesPath(): String? {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Alarms.
     *
     * @return the path of /storage/emulated/0/Alarms
     */
    public fun getExternalAlarmsPath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Notifications.
     *
     * @return the path of /storage/emulated/0/Notifications
     */
    public fun getExternalNotificationsPath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Pictures.
     *
     * @return the path of /storage/emulated/0/Pictures
     */
    public fun getExternalPicturesPath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Movies.
     *
     * @return the path of /storage/emulated/0/Movies
     */
    public fun getExternalMoviesPath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Download.
     *
     * @return the path of /storage/emulated/0/Download
     */
    public fun getExternalDownloadsPath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/DCIM.
     *
     * @return the path of /storage/emulated/0/DCIM
     */
    public fun getExternalDcimPath(): String {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Documents.
     *
     * @return the path of /storage/emulated/0/Documents
     */
    public fun getExternalDocumentsPath(): String {
        if (isExternalStorageDisable()) return "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package.
     *
     * @return the path of /storage/emulated/0/Android/data/package
     */
    public fun getExternalAppDataPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalCacheDir().getParentFile().getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/cache.
     *
     * @return the path of /storage/emulated/0/Android/data/package/cache
     */
    public fun getExternalAppCachePath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalCacheDir().getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files
     */
    public fun getExternalAppFilesPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(null).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Music.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Music
     */
    public fun getExternalAppMusicPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Podcasts.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Podcasts
     */
    public fun getExternalAppPodcastsPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Ringtones.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Ringtones
     */
    public fun getExternalAppRingtonesPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_RINGTONES).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Alarms.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Alarms
     */
    public fun getExternalAppAlarmsPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_ALARMS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Notifications.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Notifications
     */
    public fun getExternalAppNotificationsPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Pictures.
     *
     * @return path of /storage/emulated/0/Android/data/package/files/Pictures
     */
    public fun getExternalAppPicturesPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Movies.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Movies
     */
    public fun getExternalAppMoviesPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Download.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Download
     */
    public fun getExternalAppDownloadPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/DCIM.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/DCIM
     */
    public fun getExternalAppDcimPath(): String {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Documents.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Documents
     */
    public fun getExternalAppDocumentsPath(): String {
        if (isExternalStorageDisable()) return "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            //noinspection ConstantConditions
            return context.getExternalFilesDir(null).getAbsolutePath() + "/Documents";
        }
        //noinspection ConstantConditions
        return context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/obb/package.
     *
     * @return the path of /storage/emulated/0/Android/obb/package
     */
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    public fun getExternalAppObbPath(): String {
        if (isExternalStorageDisable()) return "";
        return context.getObbDir().getAbsolutePath();
    }

    private fun isExternalStorageDisable(): Boolean {
        return !Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}