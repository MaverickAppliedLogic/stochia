package com.example.stochia

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stochia.core.calculation_system.python.LocalEngineServiceImpl
import junit.framework.TestCase
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalEngineTest{

    @Test
    fun test_get_distribution() {
        //Given
        val data = listOf(40,3,2,4,3,45,5,2,3,4)

        //When
        val result = LocalEngineServiceImpl.get_distribution(data)

        //Then
        assertTrue(result.frecuencias.isNotEmpty())
        assertTrue(result.probabilidades.isNotEmpty())
        assertTrue(result.frecuencias.size == result.probabilidades.size)
        assertTrue(result.min <= result.max)
        assertTrue(result.p5 <= result.p95)
        assertTrue(result.total > 0)
    }

    @Test
    fun test_gen_montecarlo(){
        //Given

        //When
        //Then
    }

    @Test
    fun test_gen_markov(){
        //Given
        //When
        //Then
    }
}