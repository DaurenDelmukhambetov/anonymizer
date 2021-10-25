package org.dauren.repository

interface DictionaryRepository {

    fun username(toReplace: String, ignoreCase: Boolean = true): String

    fun domainName(toReplace: String): String

    fun digits(toReplace: String): String

}
