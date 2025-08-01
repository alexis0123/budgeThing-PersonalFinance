package com.budgething.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.budgething.data.local.AppDatabaseCallback
import com.budgething.data.helper.LocalDateConverter
import com.budgething.data.helper.SubList
import com.budgething.data.local.expense.Expense
import com.budgething.data.local.expense.ExpenseDao
import com.budgething.data.local.expense.category.Category
import com.budgething.data.local.expense.category.CategoryDao
import com.budgething.data.local.expense.item.Item
import com.budgething.data.local.expense.item.ItemDao
import com.budgething.data.local.income.Income
import com.budgething.data.local.income.IncomeDao
import com.budgething.data.local.income.IncomeType
import com.budgething.data.local.income.type.IncomeTypeDao

@Database(
    entities = [
        Expense::class,
        Item::class,
        Category::class,
        Income::class,
        IncomeType::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    SubList::class,
    LocalDateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun itemDao(): ItemDao
    abstract fun categoryDao(): CategoryDao
    abstract fun incomeDao(): IncomeDao
    abstract fun incomeTypeDao(): IncomeTypeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "budgething_db"
                )
                    .addCallback(AppDatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}