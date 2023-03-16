package com.example.adfmp_settings

import org.junit.Test

import org.junit.Assert.*

class NumberFromDbTest {
    private val db = mapOf(
        'a' to listOf("Assembler"),
        'b' to listOf("Basic"),
        'r' to listOf("R")
    )

    @Test
    fun selectNone() {
        assertEquals(0, selectNumberFromDb(db, 0).size)
    }
    @Test
    fun selectAll() {
        val result = selectNumberFromDb(db, db.size)
        assertEquals(db.size, result.size)
        assertEquals(listOf("Assembler"), db['a'])
        assertEquals(listOf("Basic"), db['b'])
        assertEquals(listOf("R"), db['r'])
    }
    @Test
    fun selectMoreThanAll() {
        val result = selectNumberFromDb(db, db.size + 10)
        assertEquals(db.size, result.size)
        assertEquals(listOf("Assembler"), db['a'])
        assertEquals(listOf("Basic"), db['b'])
        assertEquals(listOf("R"), db['r'])
    }
    @Test
    fun selectSome() {
        val result = selectNumberFromDb(db, 1)
        assertEquals(1, result.size)
        for(key in db.keys) {
            if(result[key] == null) continue
            assertTrue(db[key]!!.containsAll(result[key]!!))
        }
    }
}