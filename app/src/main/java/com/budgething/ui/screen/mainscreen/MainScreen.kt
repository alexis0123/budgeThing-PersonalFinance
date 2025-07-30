package com.budgething.ui.screen.mainscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.budgething.ui.screen.mainscreen.pages.ExpensePage
import com.budgething.ui.screen.mainscreen.pages.IncomePage
import com.budgething.ui.screen.mainscreen.pages.ItemPage
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun MainScreen() {
    Column {
        SpacerBar()
        PagerNav()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerNav() {
    val tabs = listOf("Expense", "Items", "Income")
    val pagerState = rememberPagerState(initialPage = 1, pageCount = { tabs.size })

    Box(modifier = Modifier.fillMaxSize()) {
        FloatingPageTitle(tabs = tabs, pagerState = pagerState)

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 5.dp,
            shadowElevation = 10.dp
        ) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> ExpensePage()
                    1 -> ItemPage()
                    2 -> IncomePage()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FloatingPageTitle(tabs: List<String>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage
    val offset = pagerState.currentPageOffsetFraction

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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

@Composable
fun SpacerBar() {
    Box(modifier = Modifier.fillMaxWidth().height(50.dp).background(MaterialTheme.colorScheme.background))
}