package com.example.adfmp_settings

import kotlin.random.Random

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

fun selectNumberFromDb(db: Map<Char, List<String>>, number: Int): Map<Char, MutableList<String>> {
    val allWords = mutableListOf<String>()
    for (lst in db.values) {
        allWords.addAll(lst)
    }
    val result: MutableMap<Char, MutableList<String>> = mutableMapOf()
    for(i in 0 until number) {
        if(allWords.isEmpty()) break
        val randomIndex = Random.nextInt(allWords.size)
        val randomElement = allWords[randomIndex]
        allWords.removeAt(randomIndex)
        val key = randomElement[0].lowercaseChar()
        if(result.get(key) == null) {
            result.put(key, mutableListOf())
        }
        result[key]!!.add(randomElement)
    }
    return result
}