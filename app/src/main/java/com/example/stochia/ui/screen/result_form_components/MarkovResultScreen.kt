package com.example.stochia.ui.screen.result_form_components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.markov.MarkovResult

@Composable
fun MarkovResultScreen(
    result: MarkovResult,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    val statePath = result.path
    val transitionProbs = result.probs

    val uniqueStates = remember(statePath) {
        statePath.toSet().toList()   // Orden según aparición
    }

    val n = uniqueStates.size

    // Reconstruimos la matriz NxN desde la lista plana
    val matrix = remember(transitionProbs) {
        List(n) { row ->
            List(n) { col ->
                transitionProbs[row * n + col]
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Markov Summary",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(12.dp))

            // Recorrido de estados
            Text(
                text = "State Path:",
                style = MaterialTheme.typography.titleMedium
            )

            statePath.forEachIndexed { index, state ->
                Text(
                    text = "t=${index.toString().padStart(2, '0')} → $state",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(16.dp))

            // Matriz de transición
            Text(
                text = "Transition Matrix (P[i→j]):",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(8.dp))

            // Encabezado
            Row {
                Text("     ", modifier = Modifier.width(40.dp))
                uniqueStates.forEach { s ->
                    Text(
                        text = s,
                        modifier = Modifier.width(60.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Filas de la matriz
            matrix.forEachIndexed { rowIndex, row ->
                Row {
                    Text(
                        text = uniqueStates[rowIndex],
                        modifier = Modifier.width(40.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    row.forEach { prob ->
                        Text(
                            text = String.format("%.3f", prob),
                            modifier = Modifier.width(60.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
