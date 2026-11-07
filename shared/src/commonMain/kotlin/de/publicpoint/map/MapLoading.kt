package de.publicpoint.map

import androidx.compose.animation.core.tween
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import de.publicpoint.shared.resources.Res
import de.publicpoint.shared.resources.map_loading
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

private val LightBase = Color(0xFFF2F3F0)
private val DarkBase = Color(0xFF45516E)

@Composable
internal fun MapLoading(dark: Boolean, visible: Boolean) {
    AnimatedVisibility(visible, exit = fadeOut(tween(300))) {
        val description = stringResource(Res.string.map_loading)
        val dot = if (dark) Color.White.copy(alpha = 0.6f) else Color(0xFF6B7280)
        var step by remember { mutableIntStateOf(0) }
        LaunchedEffect(Unit) {
            while (true) {
                delay(250)
                step++
            }
        }
        Box(Modifier.fillMaxSize().background(if (dark) DarkBase else LightBase).semantics { contentDescription = description }) {
            Canvas(Modifier.align(Alignment.Center).size(56.dp, 16.dp)) {
                val active = step % 3
                repeat(3) { i ->
                    val on = i == active
                    drawCircle(if (on) dot else dot.copy(alpha = dot.alpha * 0.4f), radius = if (on) 5.dp.toPx() else 4.dp.toPx(), center = Offset(4.dp.toPx() + i * 24.dp.toPx(), size.height / 2))
                }
            }
        }
    }
}
