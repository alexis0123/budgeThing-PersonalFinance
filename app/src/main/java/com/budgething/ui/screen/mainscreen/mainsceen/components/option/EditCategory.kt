package com.budgething.ui.screen.mainscreen.mainsceen.components.option

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.budgething.data.local.expense.category.Category
import com.budgething.ui.screen.mainscreen.pages.viewmodel.CategoryViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization

@Composable
fun EditCategory(
    showDialog: Boolean,
    dismiss: () -> Unit,
    viewModel: CategoryViewModel
) {
    val elevatedColor = MaterialTheme.colorScheme.surfaceColorAtElevation(200.dp)

    val categories by viewModel.category.collectAsState()

    var showSub by remember { mutableStateOf(false) }
    var selectedCategory: Category? by remember { mutableStateOf(null) }

    if (showSub && selectedCategory != null) {
        EditSubCategory(
            showDialog = showSub,
            dismiss = { showSub = false },
            category = selectedCategory!!,
            viewModel = viewModel
        )
    }

    if (showDialog) {
        Dialog(onDismissRequest = dismiss ) {
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(400.dp)
                    .padding(10.dp)
                    .background(color = elevatedColor, shape = RoundedCornerShape(30.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Text(
                        "Main Categories",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    LazyColumn(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        items(categories) { category ->
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .clickable {
                                        showSub = true
                                        selectedCategory = category
                                    }
                            ) {
                                Text(
                                    text = category.main,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EditSubCategory(
    showDialog: Boolean,
    dismiss: () -> Unit,
    category: Category,
    viewModel: CategoryViewModel
) {
    val elevatedColor = MaterialTheme.colorScheme.surfaceColorAtElevation(200.dp)

    var subCategories by remember(category) { mutableStateOf(category.sub) }
    var textValue by remember { mutableStateOf("") }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var subCategoryToDelete by remember { mutableStateOf<String?>(null) }

    if (showDeleteDialog && subCategoryToDelete != null) {
        Dialog(onDismissRequest = { showDeleteDialog = false }) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Delete '${subCategoryToDelete}'?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(modifier = Modifier.align(Alignment.End)) {
                        Button(onClick = { showDeleteDialog = false }) {
                            Text("Cancel")
                        }
                        Button(
                            onClick = {
                                subCategories = subCategories.filter { it != subCategoryToDelete }
                                viewModel.editCategory(category.copy(sub = subCategories))
                                showDeleteDialog = false
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }



    if (showDialog) {
        Dialog(onDismissRequest = dismiss ) {
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(400.dp)
                    .padding(10.dp)
                    .background(color = elevatedColor, shape = RoundedCornerShape(30.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Sub Categories for '${category.main}'",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        items(subCategories) { subCategory ->
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp).clickable {
                                        subCategoryToDelete = subCategory
                                        showDeleteDialog = true
                                    }
                                    .padding(horizontal = 8.dp)
                            ) {
                                Text(
                                    text = subCategory,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = textValue,
                            onValueChange = { textValue = it },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences
                            )
                        )
                        Button(
                            onClick = {
                                if (textValue.isNotBlank()) {
                                    subCategories = subCategories + textValue.trim()
                                    viewModel.editCategory(category.copy(sub = subCategories))
                                    textValue = ""
                                }
                            }
                        ) {
                            Text("Add")
                        }
                    }
                }
            }
        }
    }
}