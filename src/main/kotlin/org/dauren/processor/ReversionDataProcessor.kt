package org.dauren.processor

/**
 * The processor transforms sensitive data by reversing values
 *
 * This implementation is not practical but good for testing and debugging usage
 */
class ReversionDataProcessor : AbstractDataTransformationProcessor() {

    override fun transformId(matchResult: MatchResult): String {
        return matchResult.value.reversed()
    }

    override fun doTransformEmailUsername(username: String): String {
        return username.reversed()
    }

    override fun doTransformEmailDomainName(domainName: String): String {
        return domainName.split(".").joinToString(separator = ".", transform = String::reversed)
    }

    override fun doTransformUrlDomainName(domainName: String): String {
        return domainName
            .split(".")
            .joinToString(separator = ".", transform = this::doTransformUrlSegment)
    }

    private fun doTransformUrlSegment(segment: String): String {
        return segment.reversed()
    }
}
