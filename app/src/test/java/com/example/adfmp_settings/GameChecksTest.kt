package com.example.adfmp_settings

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class BotTest {
    @Test
    fun surrenderOnEmptyKnowledgeBase() {
        val bot = Bot(mapOf(), LocalMemoryMockWordVerifier(mapOf('t' to listOf("tty"))))
        assertNull(bot.reply("test"))
    }

    @Test
    fun correctAnswerOnRequest() {

    }

    @Test
    fun doesNotRepeatUserWords() {
        val db = mapOf(
            'a' to listOf("ABAP"),
            'p' to listOf("Pascal"),
            'l' to listOf("Lisp", "Lua")
        )
        val verifier = LocalMemoryMockWordVerifier(db) as WordVerifier
        val botDb = mapOf(
            'a' to mutableListOf("ABAP"),
            'p' to mutableListOf("Pascal"),
            'l' to mutableListOf("Lisp", "Lua")
        )
        val bot = Bot(botDb, verifier)
        assertTrue(verifier.verify("ABAP") == WordVerifier.VerifierVerdict.OK)
        assertEquals(bot.reply("ABAP"), "Pascal")
        assertTrue(verifier.verify("Lua") == WordVerifier.VerifierVerdict.OK)
        assertNull(bot.reply("Lua"))
    }
}