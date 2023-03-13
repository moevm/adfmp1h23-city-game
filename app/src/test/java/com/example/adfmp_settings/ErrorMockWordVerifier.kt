package com.example.adfmp_settings

class ErrorMockWordVerifier : WordVerifier {
    override fun verify(word: String): WordVerifier.VerifierVerdict {
        return WordVerifier.VerifierVerdict.INTERNAL_ERROR
    }
}