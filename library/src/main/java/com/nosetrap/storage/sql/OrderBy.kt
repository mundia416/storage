package com.nosetrap.storage.sql

data class OrderBy(var column :String,var method: Method = Method.ASC){
    companion object {
    enum class Method{
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