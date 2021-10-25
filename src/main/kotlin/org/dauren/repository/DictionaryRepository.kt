package org.dauren.repository

interface DictionaryRepository {

    fun username(toReplace: String, ignoreCase: Boolean = true): String

    fun domainName(toReplace: String): String

    fun id(toReplace: String): String

}
