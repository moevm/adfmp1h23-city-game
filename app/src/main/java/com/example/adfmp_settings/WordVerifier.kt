package com.example.adfmp_settings

interface WordVerifier {
    public enum class VerifierVerdict {
        INTERNAL_ERROR,
        OK,
        ALREADY_USED,
        LETTER_MISMATCH,
        UNKNOWN_WORD
    }

    fun verify(word: String): VerifierVerdict
}