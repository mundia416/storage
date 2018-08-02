package com.nosetrap.storage.sql

/**
 * a data class which defines how to order a query
 */
data class OrderBy(var column :String,var method: Method = Method.ASC) {

    companion object {

        /**
         * defines the order in which the results of a query should be, ascending or descending
         */
        enum class Method {
            ASC,
            DESC
        }


        internal fun convertMethodToString(method: Method): String {
            return if (method == Method.ASC) {
                "ASC"
            } else {
                "DESC"
            }
        }
    }
}