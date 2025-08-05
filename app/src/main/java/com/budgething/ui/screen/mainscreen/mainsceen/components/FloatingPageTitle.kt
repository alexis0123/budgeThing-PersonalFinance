package com.budgething.ui.screen.mainscreen.mainsceen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.budgething.ui.screen.mainscreen.mainsceen.tabPadding
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FloatingPageTitle(tabs: List<String>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage
    val offset = pagerState.currentPageOffsetFraction

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = tabPadding),
        horizontalArrangement = Arrangement.Center
    ) {
        tabs.forEachIndexed { index, title ->
            val focus = 1f - abs(currentPage + offset - index).coerceIn(0f, 1f)

            val animatedScale by animateFloatAsState(
                targetValue = lerp(0.85f, 1.2f, focus),
                label = "scaleAnim"
            )
            val animatedAlpha by animateFloatAsState(
                targetValue = lerp(0.4f, 1f, focus),
                label = "alphaAnim"
            )
            val animatedWeight = FontWeight(
                lerp(
                    FontWeight.Normal.weight.toFloat(),
                    FontWeight.Bold.weight.toFloat(),
                    focus
                ).toInt()
            )

            Text(
                text = title,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .graphicsLayer {
                        scaleX = animatedScale
                        scaleY = animatedScale
                        alpha = animatedAlpha
                    }
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = animatedWeight,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}