package com.example.stochia.ui.screen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.stochia.ui.screen.result_form_components.SingleResultCard
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.viewmodel.MainScreenEvent
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResultScreen(
    studies: List<Study> = emptyList(),
    result: Result?,
    isResultNew: Boolean,
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
                    .height(200.dp)
                    .background(color = NeutralDarker))
            {
                itemsIndexed(studies){ _, study ->
                    val type=  when(study.result){
                        is DistributionResult -> {
                         "Distribución - media: " + "%.2f".format(study.result.mean)
                        }
                        is MontecarloResult ->  "Montecarlo - Dist: ${study.result.distribution}"
                        is MarkovResult -> "Markov - Pasos: ${study.result.path.size}"
                        else -> "Unknown"
                    }
                    val date = Instant.ofEpochMilli(study.id.toLong())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()
                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    val formatted = date.format(formatter)

                    SingleResultCard(
                        title = type,
                        stats = formatted,
                        onClick = { onEvent(MainScreenEvent.StudyCardClicked(study.id)) },
                        modifier = Modifier.fillMaxHeight(0.8f).padding(10.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(
                                onClick = { onEvent(MainScreenEvent.ReuseStudyParamsClicked(study.id)) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Refresh,
                                    contentDescription = "Repetir con los mismos parámetros",
                                    tint = androidx.compose.ui.graphics.Color.White
                                )
                            }
                            if (study.result is DistributionResult) {
                                IconButton(
                                    onClick = { onEvent(MainScreenEvent.UseDistributionResultAsMontecarlo(study.id)) }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.PlayArrow,
                                        contentDescription = "Ejecutar Montecarlo con estos datos",
                                        tint = androidx.compose.ui.graphics.Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        when (result) {
            is DistributionResult -> {
                DistributionResultScreen(
                    result = result,
                    isResultNew = isResultNew,
                    modifier = Modifier,
                    onEvent = onEvent
                )
            }

            is MontecarloResult -> {
                MontecarloResultScreen(
                    result = result,
                    isResultNew = isResultNew,
                    modifier = Modifier,
                    onEvent = onEvent
                )
            }

            is MarkovResult -> {
                MarkovResultScreen(
                    result = result,
                    isResultNew = isResultNew,
                    modifier = Modifier,
                    onEvent = onEvent
                )
            }

        }

    }
}