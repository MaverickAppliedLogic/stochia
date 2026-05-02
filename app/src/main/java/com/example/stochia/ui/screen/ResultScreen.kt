package com.example.stochia.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.interfaces.Result
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.domain.model.study.Study
import com.example.stochia.ui.screen.result_form_components.DistributionResultScreen
import com.example.stochia.ui.screen.result_form_components.MarkovResultScreen
import com.example.stochia.ui.screen.result_form_components.MontecarloResultScreen
import com.example.stochia.ui.viewmodel.MainScreenEvent

@Composable
fun ResultScreen(
    studies: List<Study> = emptyList(),
    result: Result?,
    onEvent: (MainScreenEvent) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        if (studies.isNotEmpty()){
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)){
                itemsIndexed(studies){ index, study ->
                    Card(
                        modifier = Modifier.fillMaxHeight(0.8f)
                    ) {
                        Text(text = study.name)
                        Text(text = study.result.toString())
                    }
                }
            }
        }
        when (result) {
            is DistributionResult -> {
                DistributionResultScreen(
                    result = result,
                    modifier = Modifier,
                    onEvent = onEvent
                )
            }

            is MontecarloResult -> {
                MontecarloResultScreen(
                    result = result,
                    modifier = Modifier,
                    onEvent = onEvent
                )
            }

            is MarkovResult -> {
                MarkovResultScreen(
                    result = result,
                    modifier = Modifier,
                    onEvent = onEvent
                )
            }

        }

    }
}