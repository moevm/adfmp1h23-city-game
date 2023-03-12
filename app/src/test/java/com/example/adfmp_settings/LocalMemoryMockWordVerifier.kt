package com.example.adfmp_settings

class LocalMemoryMockWordVerifier(private val db: Map<Char, List<String>>) : WordVerifier {
    private val history: MutableList<String> = mutableListOf()

    override fun verify(word: String): WordVerifier.VerifierVerdict {
        val lastIndex = history.lastIndex
        if(lastIndex != -1 && history[lastIndex][history[lastIndex].length-1] != word[0]) {
            return WordVerifier.VerifierVerdict.LETTER_MISMATCH
        }
        if(db[word[0]] == null || word !in db[word[0]]!!) {
            return WordVerifier.VerifierVerdict.UNKNOWN_WORD
        }
        if(word in history) {
            return WordVerifier.VerifierVerdict.ALREADY_USED
        }
        history.add(word)
        return WordVerifier.VerifierVerdict.OK
    }
}