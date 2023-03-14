package com.example.adfmp_settings

class LocalMemoryWordVerifier(private val db: Map<Char, List<String>>) : WordVerifier {
    private val history: MutableList<String> = mutableListOf()

    override fun verify(word: String): WordVerifier.VerifierVerdict {
        val chr: Char = word[0].lowercaseChar()
        val lastIndex = history.lastIndex
        if(lastIndex != -1 && history[lastIndex][history[lastIndex].length-1] != chr) {
            return WordVerifier.VerifierVerdict.LETTER_MISMATCH
        }
        if(db[chr] == null || word.lowercase() !in (db[chr]!!).map { it.lowercase() }) {
            return WordVerifier.VerifierVerdict.UNKNOWN_WORD
        }
        if(word.lowercase() in history) {
            return WordVerifier.VerifierVerdict.ALREADY_USED
        }
        history.add(word.lowercase())
        return WordVerifier.VerifierVerdict.OK
    }
}