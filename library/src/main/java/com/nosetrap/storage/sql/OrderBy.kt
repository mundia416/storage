package com.nosetrap.storage.sql

/**
 * a data class which defines how to order a query
 */
data class OrderBy(var column :String,var method: Method = Method.ASC) {

    companion object {


        internal fun convertMethodToString(method: Method): String {
            return if (method == Method.ASC) {
                "ASC"
            } else {
                "DESC"
            }
        }
    }
}