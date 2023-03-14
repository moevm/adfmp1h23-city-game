package com.example.adfmp_settings

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class BotTest {
    @Test
    fun surrenderOnEmptyKnowledgeBase() {
        val bot = Bot(mapOf(), HappyMockWordVerifier())
        assertNull(bot.reply("test"))
    }

    @Test
    fun correctAnswerOnRequest() {
        val verifier = HappyMockWordVerifier() as WordVerifier
        val bot = Bot(mapOf(
            'a' to mutableListOf("ABAP"),
            'o' to mutableListOf("OCaml"),
            'x' to mutableListOf("XML", "XeLaTeX")
        ), verifier)
        assertEquals("ABAP", bot.reply("Ada"))
        assertEquals("OCaml", bot.reply("Go"))
        assertEquals("XML", bot.reply("TeX"))
        assertEquals("XeLaTeX", bot.reply("LaTeX"))
    }

    @Test
    fun doesNotRepeatUserWords() {
        val verifier = BaselessMockWordVerifier() as WordVerifier
        val botDb = mapOf(
            'a' to mutableListOf("ABAP"),
            'p' to mutableListOf("Pascal"),
            'l' to mutableListOf("Lisp", "Lua")
        )
        val bot = Bot(botDb, verifier)
        assertTrue(verifier.verify("ABAP") == WordVerifier.VerifierVerdict.OK)
        assertEquals("Pascal", bot.reply("ABAP"))
        assertTrue(verifier.verify("Lua") == WordVerifier.VerifierVerdict.OK)
        assertNull(bot.reply("Lua"))
    }

    @Test
    fun surrenderOnVerifyError() {
        val bot = Bot(mapOf(
            'a' to mutableListOf("ABAP"),
            'p' to mutableListOf("Pascal"),
            'l' to mutableListOf("Lisp", "Lua")
        ), ErrorMockWordVerifier())
        assertNull(bot.reply("Go"))
        assertNull(bot.reply("Ada"))
    }
}