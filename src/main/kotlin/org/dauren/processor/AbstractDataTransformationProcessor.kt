package org.dauren.processor

abstract class AbstractDataTransformationProcessor : DataTransformationProcessor {

    override fun transformEmails(matchResult: MatchResult): String {
        val email = matchResult.value
        val atIndex = email.indexOf("@")
        val username = email.substring(0, atIndex)
        val host = email.substring(atIndex + 1)
        return "${doTransformEmailUsername(username)}@${doTransformEmailDomainName(host)}"
    }

    override fun transformUrls(matchResult: MatchResult): String {
        val url = matchResult.value
        val domainNameStartIndex = url.indexOf("://") + 3
        val domainNameEndIndex = url.indexOf("/", startIndex = domainNameStartIndex)
            .let { if (it == -1) url.length-1 else it }
        val protocol = url.substring(0, domainNameStartIndex)
        val path = url.substring(domainNameEndIndex)
        val domainName = url.substring(domainNameStartIndex, domainNameEndIndex)
        return "$protocol${doTransformUrlDomainName(domainName)}$path"
    }

    protected abstract fun doTransformEmailUsername(username: String): String

    protected abstract fun doTransformEmailDomainName(domainName: String): String

    protected abstract fun doTransformUrlDomainName(domainName: String): String
}