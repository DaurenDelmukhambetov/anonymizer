package org.dauren.repository

import com.github.javafaker.Faker
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * The repo provides in-memory storage for sensitive data dictionary
 * and generates a new random value for every non-existent key
 */
class InMemoryDictionaryRepository(
    private val usernames: ConcurrentMap<String, String> = ConcurrentHashMap(),
    private val domainNames: ConcurrentMap<String, String> = ConcurrentHashMap(),
    private val digits: ConcurrentMap<String, String> = ConcurrentHashMap(),
    private val faker: Faker = Faker()
): DictionaryRepository {

    override fun username(toReplace: String, ignoreCase: Boolean): String {
        val username = if (ignoreCase) toReplace.lowercase() else toReplace
        return usernames.getOrPut(username) { faker.name().username() }
    }

    override fun domainName(toReplace: String): String {
        return domainNames.getOrPut(toReplace.lowercase()) { faker.internet().domainName() }
    }

    override fun digits(toReplace: String): String {
        return digits.getOrPut(toReplace) { faker.number().digits(toReplace.length) }
    }
}