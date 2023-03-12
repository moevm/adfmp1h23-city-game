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

    }
}