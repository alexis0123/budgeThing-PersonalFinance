package com.budgething.ui.screen.mainscreen.pages.expense.dialog

import CancelRecordButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.budgething.ui.screen.mainscreen.pages.viewmodel.ExpenseViewModel
import java.util.Locale

@Composable
fun ConfirmExpenseDialog(
    showDialog: Boolean,
    amount: String,
    dismiss: () -> Unit,
    confirm: () -> Unit,
    viewModel: ConfirmExpenseViewModel,
    expenseViewModel: ExpenseViewModel
) {
    val mainCategory by viewModel.mainCategory.collectAsState()
    val subCategory by viewModel.subCategory.collectAsState()
    val name by viewModel.query.collectAsState()
    val options by viewModel.filteredOptions.collectAsState()
    val mainCategoryList by viewModel.mainCategories.collectAsState()
    val subCategoryList by viewModel.subCategories.collectAsState()

    val focusManager = LocalFocusManager.current
    var showFilter by remember { mutableStateOf(false) }

    val elevatedColor = MaterialTheme.colorScheme.surfaceColorAtElevation(200.dp)

    if (showDialog) {
        Dialog(onDismissRequest = { showFilter = false }) {
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(400.dp)
                    .padding(10.dp)
                    .background(color = elevatedColor, shape = RoundedCornerShape(30.dp))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        focusManager.clearFocus()
                        showFilter = false
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 35.dp)
                        .padding(bottom = 25.dp)
                        .padding(horizontal = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = String.format(Locale.US, "\u20B1%,.2f", amount.toDouble()),
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Dropdown(
                            itemsList = mainCategoryList,
                            selectedItem = mainCategory,
                            label = "Main Category",
                            onSelect = {
                                viewModel.setMainCategoryTo(it)
                                viewModel.clearSubCategory()
                            },
                            onRemove = {
                                viewModel.clearMainCategory()
                                viewModel.clearSubCategory()
                            }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Dropdown(
                            itemsList = subCategoryList,
                            selectedItem = subCategory,
                            label = "Sub Category",
                            onSelect = {
                                viewModel.setSubCategoryTo(it)
                                viewModel.fillMainCategory()
                            },
                            onRemove = {
                                viewModel.clearSubCategory()
                            }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        SearchableDropDown(
                            query = name,
                            filteredOptions = options,
                            onQueryChange = { viewModel.changeQuery(it) },
                            onItemSelected = {
                                viewModel.changeQuery(it.name)
                                viewModel.setMainCategoryTo(it.mainCategory)
                                viewModel.setSubCategoryTo(it.subCategory)
                                viewModel.setDone()
                            },
                            showFilter = showFilter,
                            setShowFilter = { showFilter = it }
                        )
                    }

                    Box(
                        modifier = Modifier

                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        CancelRecordButton(
                            dismiss = {
                                dismiss()
                                viewModel.clearAllState()
                            },
                            mainCategory = mainCategory,
                            subCategory = subCategory,
                            name = name,
                            amount = amount.toDouble(),
                            filteredOption = options,
                            viewModel = expenseViewModel,
                            confirmExpenseViewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}