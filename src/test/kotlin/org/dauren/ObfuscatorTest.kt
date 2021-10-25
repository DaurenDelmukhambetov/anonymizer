package org.dauren

import org.dauren.processor.ReversionDataProcessor
import org.dauren.processor.SubstitutionDataProcessor
import org.dauren.repository.InMemoryDictionaryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

internal class ObfuscatorTest {

    @Nested
    inner class WithSimpleReversionDataProcessor {

        private val obfuscator = Obfuscator(transformationProcessor = ReversionDataProcessor())

        @ParameterizedTest
        @CsvFileSource(resources = ["/reversed-test-data-set.csv"], delimiter = '|')
        fun `should return text with sensitive data masked by reversion`(input: String, expected: String) {
            val maskedText = obfuscator.obfuscate(input)
            assertEquals(expected, maskedText)
        }

        @Test
        fun `should return blank line when input is blank`() {
            val maskedText = obfuscator.obfuscate("")
            assertEquals("", maskedText)
        }
    }

    @Nested
    inner class WithSubstitutionDataProcessor {

        private val inMemoryDictionaryRepository = InMemoryDictionaryRepository(
            digits = ConcurrentHashMap(mapOf("87432" to "45678", "789" to "010", "375" to "707")),
            domainNames = ConcurrentHashMap(
                mapOf(
                    "case.ai" to "somewhere.net",
                    "www.google.com" to "www.sample.io",
                    "google.com" to "sample.io"
                )
            ),
            usernames = ConcurrentHashMap(mapOf("adam.smith" to "economist", "isaac.newton" to "physicist"))
        )

        private val obfuscator = Obfuscator(
            transformationProcessor = SubstitutionDataProcessor(dictionaryRepository = inMemoryDictionaryRepository)
        )

        @ParameterizedTest
        @CsvFileSource(resources = ["/replaced-test-data-set.csv"], delimiter = '|')
        fun `should return text with sensitive data masked by substitution with pre-defined values`(input: String, expected: String) {
            val maskedText = obfuscator.obfuscate(input)
            assertEquals(expected, maskedText)
        }

        @Test
        fun `should return text with sensitive data masked by substitution with random value`() {
            val maskedText = obfuscator.obfuscate("Hello, adele@music.com! :heart It's me from 2023! Let's meet on [ILoveAdele.com](https://ILoveAdele.com)")
            assertTrue(maskedText.startsWith("Hello, "))
            assertFalse(maskedText.contains("adele@music.com"))
            assertFalse(maskedText.contains("2023"))
            assertFalse(maskedText.contains("https://ILoveAdele.com"))
        }

        @Test
        fun `should return text with consistent email`() {
            val maskedText = obfuscator.obfuscate("Hello, adele@music.com! I sent you an email to adele@music.com")
            assertFalse(maskedText.contains("adele@music.com"))
            val maskedEmail = maskedText.split(" ").last();
            assertTrue(maskedText.matches(Regex("Hello, $maskedEmail! I sent you an email to $maskedEmail")))
        }
    }
}