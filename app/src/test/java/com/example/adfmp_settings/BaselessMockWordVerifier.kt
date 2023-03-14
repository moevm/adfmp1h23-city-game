package com.example.adfmp_settings

class BaselessMockWordVerifier : WordVerifier {
    private val history: MutableList<String> = mutableListOf()

    override fun verify(word: String): WordVerifier.VerifierVerdict {
        val lastIndex = history.lastIndex
        if(lastIndex != -1 && history[lastIndex][history[lastIndex].length-1].lowercaseChar()
            != word[0].lowercaseChar()) {
            return WordVerifier.VerifierVerdict.LETTER_MISMATCH
        }
        if(word in history) {
            return WordVerifier.VerifierVerdict.ALREADY_USED
        }
        history.add(word)
        return WordVerifier.VerifierVerdict.OK
    }
}