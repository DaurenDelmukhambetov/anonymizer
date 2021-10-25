package org.dauren.processor

/**
 * The processor masks sensitive data
 */
interface DataTransformationProcessor {

    fun transformDigits(matchResult: MatchResult): String

    fun transformEmail(matchResult: MatchResult): String

    fun transformUrl(matchResult: MatchResult): String

}