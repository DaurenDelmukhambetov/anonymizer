package org.dauren

import org.dauren.processor.DataTransformationProcessor
import org.dauren.processor.SubstitutionDataProcessor

class Obfuscator(
    private val transformationProcessor: DataTransformationProcessor = SubstitutionDataProcessor()
) {

    fun obfuscate(input: String): String {
        if (input.isBlank()) return input

        val withReplacedUrls = URL_PATTERN.replace(input, transformationProcessor::transformUrl)
        val withReplacedDigits = DIGITS_PATTERN.replace(withReplacedUrls, transformationProcessor::transformId)
        return EMAIL_PATTERN.replace(withReplacedDigits, transformationProcessor::transformEmail)
    }

    companion object {
        private val DIGITS_PATTERN = Regex("[0-9]{3,}")
        private val EMAIL_PATTERN = Regex("([0-9A-Za-z-\\._])+@([0-9A-Za-z])+[\\.]?([0-9A-Za-z]){1,5}")
        private val URL_PATTERN = Regex("((http|https|HTTP|HTTPS):\\/\\/)(www.|WWW.)?([a-zA-Z]+\\.)+([a-zA-Z]+\\/?)([a-zA-Z0-9]+\\/?)*")
    }

}