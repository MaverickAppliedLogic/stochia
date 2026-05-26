package com.example.stochia.ui.screen.main_screen_components

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.NeutralDarkest
import com.example.stochia.ui.theme.PrimaryLight

@Composable
fun LoadingOverlay() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = LinearEasing)
        ),
        label = "rotation"
    )

    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        ),
        label = "pulse"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralDarkest.copy(alpha = 0.55f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .graphicsLayer {
                    rotationZ = rotation
                    scaleX = pulse
                    scaleY = pulse
                }
                .drawWithCache {
                    val strokeWidthGlow = 10.dp.toPx()
                    val strokeWidthArc = 5.dp.toPx()
                    val sweepAngle = 260f
                    val startAngle = 0f
                    val padding = strokeWidthGlow / 2f
                    val arcSize = androidx.compose.ui.geometry.Size(
                        size.width - padding * 2,
                        size.height - padding * 2
                    )
                    val topLeft = androidx.compose.ui.geometry.Offset(padding, padding)

                    onDrawWithContent {
                        // Glow layer
                        drawIntoCanvas { canvas ->
                            val glowPaint = Paint().apply {
                                style = PaintingStyle.Stroke
                                strokeWidth = strokeWidthGlow
                                strokeCap = StrokeCap.Round
                                color = PrimaryLight
                                asFrameworkPaint().apply {
                                    isAntiAlias = true
                                    maskFilter = BlurMaskFilter(16f, BlurMaskFilter.Blur.NORMAL)
                                }
                            }
                            canvas.drawArc(
                                left = topLeft.x,
                                top = topLeft.y,
                                right = topLeft.x + arcSize.width,
                                bottom = topLeft.y + arcSize.height,
                                startAngle = startAngle,
                                sweepAngle = sweepAngle,
                                useCenter = false,
                                paint = glowPaint
                            )
                        }

                        // Sharp arc layer
                        drawArc(
                            color = PrimaryLight,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            topLeft = topLeft,
                            size = arcSize,
                            style = androidx.compose.ui.graphics.drawscope.Stroke(
                                width = strokeWidthArc,
                                cap = StrokeCap.Round
                            )
                        )
                    }
                }
        )
    }
}
