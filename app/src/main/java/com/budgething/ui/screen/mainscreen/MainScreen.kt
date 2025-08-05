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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.budgething.ui.screen.mainscreen.pages.expense.ExpensePage
import com.budgething.ui.screen.mainscreen.pages.IncomePage
import com.budgething.ui.screen.mainscreen.pages.ItemPage
import com.budgething.ui.screen.mainscreen.pages.expense.NumKeyViewModel
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.ConfirmExpenseViewModel
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs

val tabPadding = 110.dp

@Composable
fun MainScreen(
    numKeyViewModel: NumKeyViewModel,
    confirmExpenseViewModel: ConfirmExpenseViewModel,
    expenseViewModel: ExpenseViewModel
) {
    Column {
        PagerNav(
            numKeyViewModel,
            confirmExpenseViewModel,
            expenseViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerNav(
    numKeyViewModel: NumKeyViewModel,
    confirmExpenseViewModel: ConfirmExpenseViewModel,
    expenseViewModel: ExpenseViewModel
) {
    val tabs = listOf("Expense", "Items", "Income")
    val pagerState = rememberPagerState(initialPage = 1, pageCount = { tabs.size })

    Box(modifier = Modifier.fillMaxSize()) {
        Title()
        FloatingPageTitle(tabs = tabs, pagerState = pagerState)

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.5.dp,
            shadowElevation = 10.dp
        ) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> ExpensePage(
                        numKeyViewModel,
                        confirmExpenseViewModel,
                        expenseViewModel
                    )
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

@Composable
fun Title() {

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .padding(horizontal = 25.dp)
            .zIndex(1f),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "BudgeThing",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Options",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .clickable(onClick = { expanded = true })
            )
        }
    }
}