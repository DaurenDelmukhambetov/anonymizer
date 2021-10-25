package org.dauren.processor

import org.dauren.repository.DictionaryRepository
import org.dauren.repository.InMemoryDictionaryRepository

/**
 * The processor masks sensitive data by replacing values
 * from given dictionary
 */
class SubstitutionDataProcessor(
    private val dictionaryRepository: DictionaryRepository = InMemoryDictionaryRepository()
) : AbstractDataTransformationProcessor() {

    override fun doTransformEmailUsername(username: String): String {
        return dictionaryRepository.username(username)
    }

    override fun doTransformEmailDomainName(domainName: String): String {
        return dictionaryRepository.domainName(domainName)
    }

    override fun doTransformUrlDomainName(domainName: String): String {
        return dictionaryRepository.domainName(domainName)
    }

    override fun transformId(matchResult: MatchResult): String {
        return dictionaryRepository.id(matchResult.value)
    }
}