package com.example.adfmp_settings

class HappyMockWordVerifier : WordVerifier {
    override fun verify(word: String): WordVerifier.VerifierVerdict {
        return WordVerifier.VerifierVerdict.OK
    }
}