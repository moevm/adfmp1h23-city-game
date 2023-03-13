package com.example.adfmp_settings

class Bot(private val knowledgeBase: Map<Char, MutableList<String>>,
          private val verifier: WordVerifier
) {

    fun reply(prevWord: String): String? {
        val possibilities: MutableList<String>? = knowledgeBase[prevWord[prevWord.length-1].lowercaseChar()]
        if(possibilities == null) return null
        val iterator = possibilities.iterator()
        while(iterator.hasNext()) {
            val option = iterator.next()
            iterator.remove()
            if(verifier.verify(option) == WordVerifier.VerifierVerdict.OK) {
                return option
            }
        }
        return null
    }
}