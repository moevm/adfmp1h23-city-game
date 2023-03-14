package com.example.adfmp_settings

import junit.framework.TestCase.assertEquals
import org.junit.Test

class StatisticUnitTest {

    @Test
    fun testOfFunnyFunc(){
        val num1 = 13
        val num2 = 3
        val num3 = 130
        assertEquals("13%", numToPercents(num1))
        assertEquals("3%", numToPercents(num2))
        assertEquals("130%", numToPercents(num3))
    }
}