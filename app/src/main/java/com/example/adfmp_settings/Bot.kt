package com.example.adfmp_settings

import kotlin.random.Random

class Bot(private val knowledgeBase: Map<Char, MutableList<String>>,
          private val verifier: WordVerifier
) {

    fun reply(prevWord: String): String? {
        var possibilities: MutableList<String>? = mutableListOf()
        for(lastIndex in prevWord.length-1 downTo 0) {
            val lastChar = prevWord[lastIndex].lowercaseChar()
            possibilities = knowledgeBase[lastChar]
            if(possibilities != null) break
        }
        if(possibilities == null) throw java.lang.IllegalStateException(
            "Entire word $prevWord seems to contain only symbols that do not start any word. " +
                    "This must be impossible in a bug-free app."
        )
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

fun selectNumberFromDb(db: Map<Char, List<String>>, number: Int): Map<Char, MutableList<String>> {
    val allWords = mutableListOf<String>()
    for (lst in db.values) {
        allWords.addAll(lst)
    }
    val result: MutableMap<Char, MutableList<String>> = mutableMapOf()
    for(key in db.keys) {
        result[key] = mutableListOf()
    }
    for(i in 0 until number) {
        if(allWords.isEmpty()) break
        val randomIndex = Random.nextInt(allWords.size)
        val randomElement = allWords[randomIndex]
        allWords.removeAt(randomIndex)
        val key = randomElement[0].lowercaseChar()
        result[key]!!.add(randomElement)
    }
    return result
}