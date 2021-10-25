package org.dauren.processor

import org.dauren.repository.InMemoryDictionaryRepository

class SubstitutionDataProcessor(
    private val dictionaryRepository: InMemoryDictionaryRepository = InMemoryDictionaryRepository()
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

    override fun transformDigits(matchResult: MatchResult): String {
        return dictionaryRepository.digits(matchResult.value)
    }
}