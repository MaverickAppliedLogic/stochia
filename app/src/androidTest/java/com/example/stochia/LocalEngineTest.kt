package com.example.stochia

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stochia.core.calculation_system.python.LocalEngineServiceImpl
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalEngineTest{

    @Test
    fun test_get_distribution() {
        //Given
        val expectedKeys = listOf(
            "frecuencias", "probabilidades", "media", "desviacion",
            "p5", "p95", "min", "max", "total_datos"
        )

        //When
        val result = LocalEngineServiceImpl.get_distribution(listOf(40,3,2,4,3,45,5,2,3,4))

        //Then
        TestCase.assertTrue(result.keys.containsAll(expectedKeys))
        result.forEach { (k, value) ->
            TestCase.assertTrue(k.isNotEmpty())
            TestCase.assertTrue(value.isNotEmpty())
        }
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