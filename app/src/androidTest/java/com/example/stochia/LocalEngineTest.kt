package com.example.stochia

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stochia.core.calculation_system.python.LocalEngineServiceImpl
import com.example.stochia.domain.model.distribution.toPy
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloType
import junit.framework.TestCase.assertEquals
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
    fun test_gen_montecarlo_bernoulli() {
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

        assertTrue(result.distribution == MontecarloType.BERNOULLI)
        assertTrue(result.results != null)
        assertTrue(result.results!!.isNotEmpty())

        val first = result.results[0]

        assertTrue(first.mean != null)
        assertTrue(first.stdDev != null)
        assertTrue(first.tries != null)
        assertTrue(first.mean!! >= 0.0)
        assertTrue(first.stdDev!! >= 0.0)
        assertTrue(first.tries!! > 0)
    }

    @Test
    fun test_gen_montecarlo_multinomial() {
        // Given
        val params = MontecarloParams(
            distribution = MontecarloType.MULTINOMIAL.key,
            params = listOf(10.0, 0.3, 0.4),
            size = 100
        )

        // When
        val result = LocalEngineServiceImpl.gen_montecarlo(params)

        // Then
        Log.d("test_gen_montecarlo_multinomial", "result: $result")

        assertTrue(result.distribution == MontecarloType.MULTINOMIAL)
        assertTrue(result.results != null)
        assertTrue(result.results!!.isNotEmpty())

        val results = result.results

        assertEquals(3, results.size)

        results.forEach { result ->
            assertTrue(result.mean != null)
            assertTrue(result.stdDev != null)
            assertTrue(result.p5 != null)
            assertTrue(result.p95 != null)

            assertTrue(result.mean!! >= 0.0)
            assertTrue(result.stdDev!! >= 0.0)

            assertTrue(result.p5!! <= result.p95!!)
        }
    }


    @Test
    fun test_montecarlo_geometrical() {
        val params = MontecarloParams(
            distribution = MontecarloType.GEOMETRICAL.key,
            params = listOf(0.4),
            size = 1
        )

        val result = LocalEngineServiceImpl.gen_montecarlo(params)

        Log.d("test_montecarlo_geometrical", "result=$result")

        assertTrue(result.distribution == MontecarloType.GEOMETRICAL)
        assertTrue(result.tries != null)

    }

    @Test
    fun test_gen_montecarlo_with_values() {
        val distributionsWithValues = listOf(
            MontecarloType.EXPONENTIAL,
            MontecarloType.NORMAL,
            MontecarloType.UNIFORM,
            MontecarloType.BETA
        )

        distributionsWithValues.forEach { dist ->
            val params = MontecarloParams(
                distribution = dist.key,
                params = listOf(1.0, 2.0),
                size = 100
            )

            val result = LocalEngineServiceImpl.gen_montecarlo(params)

            Log.d("test_montecarlo_with_values", "dist=$dist result=$result")

            assertTrue(result.distribution == dist)
            assertTrue(result.values != null)
            assertTrue(result.values!!.isNotEmpty())

            assertTrue(result.mean != null)
            assertTrue(result.stdDev != null)
            assertTrue(result.p5 != null)
            assertTrue(result.p95 != null)
        }
    }

    @Test
    fun test_montecarlo_without_values() {
        val distributionsWithoutValues = listOf(
            MontecarloType.BINOMIAL,
            MontecarloType.POISSON
        )

        distributionsWithoutValues.forEach { dist ->
            val params = MontecarloParams(
                distribution = dist.key,
                params = listOf(0.5, 0.2, 0.8), // dummy params
                size = 100
            )

            val result = LocalEngineServiceImpl.gen_montecarlo(params)

            Log.d("test_montecarlo_without_values", "dist=$dist result=$result")

            assertTrue(result.distribution == dist)
            assertTrue(result.values == null)
            if (result.results!= null){
                val first = result.results[0]

                assertTrue(first.mean != null)
                assertTrue(first.stdDev != null)
                assertTrue(first.tries != null)
            }
            else if (dist == MontecarloType.GEOMETRICAL){
                assertTrue(result.tries != null)
            }
            else{
                assertTrue(result.mean != null)
                assertTrue(result.stdDev != null)
                assertTrue(result.p5 != null)
                assertTrue(result.p95 != null)
            }

        }
    }

    @Test
    fun test_gen_markov(){
        //Given
        //When
        //Then
    }
}