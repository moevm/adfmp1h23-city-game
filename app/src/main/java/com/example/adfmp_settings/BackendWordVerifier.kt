package com.example.adfmp_settings

class BackendWordVerifier : WordVerifier {
    override fun verify(word: String): WordVerifier.VerifierVerdict {
        return WordVerifier.VerifierVerdict.INTERNAL_ERROR
    }

    private var sessionCookie: String? = null
    private fun initConnection() {
        throw NotImplementedError()
    }
    private fun sendVerificationRequest(word: String) {
        throw NotImplementedError()
    }
}