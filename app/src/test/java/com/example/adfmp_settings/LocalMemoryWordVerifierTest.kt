package com.example.adfmp_settings

import org.junit.Test

import org.junit.Assert.*

class LocalMemoryWordVerifierTest {
    private val db = mapOf(
        'a' to listOf("Assembler"),
        'b' to listOf("Basic"),
        'r' to listOf("R")
    )

    @Test
    fun verifyOk() {
        val verifier: WordVerifier = LocalMemoryWordVerifier(db)
        assertEquals(WordVerifier.VerifierVerdict.OK, verifier.verify("Assembler"))
        assertEquals(WordVerifier.VerifierVerdict.OK, verifier.verify("R"))
    }
    @Test
    fun verifyLetterMismatch() {
        val verifier: WordVerifier = LocalMemoryWordVerifier(db)
        assertEquals(WordVerifier.VerifierVerdict.OK, verifier.verify("Assembler"))
        assertEquals(WordVerifier.VerifierVerdict.LETTER_MISMATCH, verifier.verify("Basic"))
    }
    @Test
    fun verifyUnknownWord() {
        val verifier: WordVerifier = LocalMemoryWordVerifier(db)
        assertEquals(WordVerifier.VerifierVerdict.UNKNOWN_WORD, verifier.verify("Cjsadk"))
    }
    @Test
    fun verifyAlreadyUsed() {
        val verifier: WordVerifier = LocalMemoryWordVerifier(db)
        assertEquals(WordVerifier.VerifierVerdict.OK, verifier.verify("R"))
        assertEquals(WordVerifier.VerifierVerdict.ALREADY_USED, verifier.verify("R"))
    }
    @Test
    fun verifyUnknownAndLetterMismatch() {
        val verifier: WordVerifier = LocalMemoryWordVerifier(db)
        assertEquals(WordVerifier.VerifierVerdict.OK, verifier.verify("Assembler"))
        assertEquals(WordVerifier.VerifierVerdict.LETTER_MISMATCH, verifier.verify("Cjsadk"))
    }
    @Test
    fun verifyAlreadyUsedAndLetterMismatch() {
        val verifier: WordVerifier = LocalMemoryWordVerifier(db)
        assertEquals(WordVerifier.VerifierVerdict.OK, verifier.verify("Assembler"))
        assertEquals(WordVerifier.VerifierVerdict.LETTER_MISMATCH, verifier.verify("Assembler"))
    }
}