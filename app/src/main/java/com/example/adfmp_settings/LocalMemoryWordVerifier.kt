package com.example.adfmp_settings

class LocalMemoryWordVerifier(private val db: Map<Char, List<String>>) : WordVerifier {
    private val history: MutableList<String> = mutableListOf()

    override fun verify(word: String): WordVerifier.VerifierVerdict {
        val chr: Char = word[0].lowercaseChar()
        val lastIndex = history.lastIndex
        if(lastIndex != -1) {
            var lastChar: Char = '\u0000'
            for(index in history[lastIndex].length-1 downTo 0) {
                lastChar = history[lastIndex][index]
                if(db[lastChar] != null) break
            }
            if(db[lastChar] == null) {
                throw java.lang.IllegalStateException(
                    "Entire word $word seems to contain only symbols that do not start any word. " +
                            "This must be impossible in a bug-free app."
                )
            }
            if(chr != lastChar) return WordVerifier.VerifierVerdict.LETTER_MISMATCH
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