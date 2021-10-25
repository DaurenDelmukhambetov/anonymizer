package org.dauren.processor

/**
 * The processor masks sensitive data
 */
interface DataTransformationProcessor {

    fun transformId(matchResult: MatchResult): String

    fun transformEmail(matchResult: MatchResult): String

    fun transformUrl(matchResult: MatchResult): String

}