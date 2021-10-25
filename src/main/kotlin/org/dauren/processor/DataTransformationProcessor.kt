package org.dauren.processor

interface DataTransformationProcessor {

    fun transformDigits(matchResult: MatchResult): String

    fun transformEmails(matchResult: MatchResult): String

    fun transformUrls(matchResult: MatchResult): String

}