package com.nosetrap.storage.exceptions

class InvalidPrefNameException(reason: String = "") : Exception("attempted to create pref with invalid name: REASON:: $reason") {
}