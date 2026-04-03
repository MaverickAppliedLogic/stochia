package com.example.stochia

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stochia.domain.model.distribution.toPy
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloType
import com.example.stochia.domain.usecase.GetDistributionUsecase
import com.example.stochia.domain.usecase.GenMarkovUsecase
import com.example.stochia.domain.usecase.GenMontecarloUsecase
import junit.framework.TestCase.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class LocalEngineTest : KoinTest {

    private val getDistribution: GetDistributionUsecase by inject()
    private val genMontecarlo: GenMontecarloUsecase by inject()
    private val genMarkov: GenMarkovUsecase by inject()

    @Test
    fun test_get_distribution() {
        val params = listOf(40,3,2,4,3,45,5,2,3,4).toPy()

        val result = getDistribution(params)

        Log.d("test_get_distribution", "result: $result")

        assertTrue(result.frequencies.isNotEmpty())
        assertTrue(result.probabilities.isNotEmpty())
        assertEquals(result.frequencies.size, result.probabilities.size)
        assertTrue(result.min <= result.max)
        assertTrue(result.p5 <= result.p95)
        assertTrue(result.total > 0)
    }

    @Test
    fun test_gen_montecarlo_bernoulli() {
        val params = MontecarloParams(
            distribution = MontecarloType.BERNOULLI.key,
            params = listOf(0.6, 100.0),
            size = 100
        )

        val result = genMontecarlo(params)

        Log.d("test_gen_montecarlo", "result: $result")

        assertEquals(MontecarloType.BERNOULLI, result.distribution)
        assertNotNull(result.results)
        assertTrue(result.results!!.isNotEmpty())

        val first = result.results[0]

        assertNotNull(first.mean)
        assertNotNull(first.stdDev)
        assertNotNull(first.tries)
        assertTrue(first.mean!! >= 0.0)
        assertTrue(first.stdDev!! >= 0.0)
        assertTrue(first.tries!! > 0)
    }

    @Test
    fun test_gen_montecarlo_multinomial() {
        val params = MontecarloParams(
            distribution = MontecarloType.MULTINOMIAL.key,
            params = listOf(10.0, 0.3, 0.4),
            size = 100
        )

        val result = genMontecarlo(params)

        Log.d("test_gen_montecarlo_multinomial", "result: $result")

        assertEquals(MontecarloType.MULTINOMIAL, result.distribution)
        assertNotNull(result.results)
        assertEquals(3, result.results!!.size)

        result.results.forEach { r ->
            assertNotNull(r.mean)
            assertNotNull(r.stdDev)
            assertNotNull(r.p5)
            assertNotNull(r.p95)
            assertTrue(r.mean!! >= 0.0)
            assertTrue(r.stdDev!! >= 0.0)
            assertTrue(r.p5!! <= r.p95!!)
        }
    }

    @Test
    fun test_montecarlo_geometrical() {
        val params = MontecarloParams(
            distribution = MontecarloType.GEOMETRICAL.key,
            params = listOf(0.4),
            size = 1
        )

        val result = genMontecarlo(params)

        Log.d("test_montecarlo_geometrical", "result=$result")

        assertEquals(MontecarloType.GEOMETRICAL, result.distribution)
        assertNotNull(result.tries)
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

            val result = genMontecarlo(params)

            Log.d("test_montecarlo_with_values", "dist=$dist result=$result")

            assertEquals(dist, result.distribution)
            assertNotNull(result.values)
            assertTrue(result.values!!.isNotEmpty())

            assertNotNull(result.mean)
            assertNotNull(result.stdDev)
            assertNotNull(result.p5)
            assertNotNull(result.p95)
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
                params = listOf(0.5, 0.2, 0.8),
                size = 100
            )

            val result = genMontecarlo(params)

            Log.d("test_montecarlo_without_values", "dist=$dist result=$result")

            assertEquals(dist, result.distribution)

            if (result.results != null) {
                val first = result.results[0]
                assertNotNull(first.mean)
                assertNotNull(first.stdDev)
                assertNotNull(first.tries)
            } else {
                assertNotNull(result.mean)
                assertNotNull(result.stdDev)
                assertNotNull(result.p5)
                assertNotNull(result.p95)
            }
        }
    }

    @Test
    fun test_gen_markov() {
        val states = listOf(0, 1, 2)

        val flatProbs = listOf(
            0.1, 0.6, 0.3,
            0.2, 0.2, 0.6,
            0.5, 0.3, 0.2
        )

        val params = MarkovParams(
            states = states,
            probs = flatProbs,
            init_state = 0,
            steps = 10
        )

        val result = genMarkov(params)

        Log.d("test_gen_markov", "result: $result")

        assertNotNull(result.path)
        assertTrue(result.path.isNotEmpty())
        assertEquals(0, result.path.first())
        assertEquals(params.steps + 1, result.path.size)
    }
}
