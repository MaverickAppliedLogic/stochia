package com.example.stochia

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stochia.core.calculation_system.python.LocalEngineServiceImpl
import com.example.stochia.domain.model.distribution.toPy
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloType
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalEngineTest{

    @Test
    fun test_get_distribution() {
        //Given
        val params = listOf(40,3,2,4,3,45,5,2,3,4).toPy()

        //When
        val result = LocalEngineServiceImpl.get_distribution(params)

        //Then
        Log.d("test_get_distribution", "result: $result")
        assertTrue(result.frequencies.isNotEmpty())
        assertTrue(result.probabilities.isNotEmpty())
        assertTrue(result.frequencies.size == result.probabilities.size)
        assertTrue(result.min <= result.max)
        assertTrue(result.p5 <= result.p95)
        assertTrue(result.total > 0)
    }

    @Test
    fun test_gen_montecarlo() {
        // Given
        val params = MontecarloParams(
            distribution = MontecarloType.BERNOULLI.key,
            params = listOf(0.6, 100.0),
            size = 100
        )

        // When
        val result = LocalEngineServiceImpl.gen_montecarlo(params)

        // Then
        Log.d("test_gen_montecarlo", "result: $result")

        // Validate parent result
        assertTrue(result.distribution == MontecarloType.BERNOULLI)
        assertTrue(result.results != null)
        assertTrue(result.results!!.isNotEmpty())

        val first = result.results[0]

        // Validate child result fields
        assertTrue(first.mean != null)
        assertTrue(first.stdDev != null)
        assertTrue(first.tries != null)

        // Optional sanity checks
        assertTrue(first.mean!! >= 0.0)
        assertTrue(first.stdDev!! >= 0.0)
        assertTrue(first.tries!! > 0)
    }


    @Test
    fun test_gen_markov(){
        //Given
        //When
        //Then
    }
}