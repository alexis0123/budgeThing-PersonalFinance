package com.budgething.ui.screen.mainscreen.mainsceen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.budgething.ui.screen.mainscreen.mainsceen.components.FloatingPageTitle
import com.budgething.ui.screen.mainscreen.mainsceen.components.Title
import com.budgething.ui.screen.mainscreen.pages.expense.ExpensePage
import com.budgething.ui.screen.mainscreen.pages.IncomePage
import com.budgething.ui.screen.mainscreen.pages.ItemPage
import com.budgething.ui.screen.mainscreen.pages.expense.NumKeyViewModel
import com.budgething.ui.screen.mainscreen.pages.expense.dialog.ConfirmExpenseViewModel
import com.budgething.ui.screen.mainscreen.pages.viewmodel.CategoryViewModel
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel

val tabPadding = 110.dp

@Composable
fun MainScreen(
    numKeyViewModel: NumKeyViewModel,
    confirmExpenseViewModel: ConfirmExpenseViewModel,
    expenseViewModel: ExpenseViewModel,
    categoryViewModel: CategoryViewModel
) {
    Column {
        PagerNav(
            numKeyViewModel,
            confirmExpenseViewModel,
            expenseViewModel,
            categoryViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerNav(
    numKeyViewModel: NumKeyViewModel,
    confirmExpenseViewModel: ConfirmExpenseViewModel,
    expenseViewModel: ExpenseViewModel,
    categoryViewModel: CategoryViewModel
) {
    val tabs = listOf("Expense", "Items", "Income")
    val pagerState = rememberPagerState(initialPage = 1, pageCount = { tabs.size })

    Box(modifier = Modifier.fillMaxSize()) {
        Title(categoryViewModel)
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