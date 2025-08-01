package com.budgething.data.local

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.budgething.data.local.expense.category.Category
import com.budgething.data.local.expense.item.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppDatabaseCallback(
    private val context: Context
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        // Prepopulate here
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.Companion.getDatabase(context)

            val categories = listOf(
                Category(
                    main = "Food",
                    sub = listOf("Groceries", "Snacks", "Dining Out", "Drinks")
                ),
                Category(
                    main = "Transportation",
                    sub = listOf("Public Transport", "Fuel", "Toll Fees", "Parking")
                ),
                Category(
                    main = "Utilities",
                    sub = listOf("Electricity", "Water", "Internet", "Mobile Load")
                ),
                Category(main = "Housing", sub = listOf("Rent", "Repairs", "Furnishings")),
                Category(main = "Health", sub = listOf("Medicine", "Check-ups", "Supplements")),
                Category(main = "Personal Care", sub = listOf("Toiletries", "Haircut", "Skincare")),
                Category(
                    main = "Entertainment",
                    sub = listOf("Streaming", "Gaming", "Movies", "Subscriptions")
                ),
                Category(
                    main = "Education",
                    sub = listOf("School Supplies", "Tuition", "Online Courses")
                ),
                Category(
                    main = "Savings",
                    sub = listOf("Emergency Fund", "Investments", "Bank Transfer")
                ),
                Category(
                    main = "Gifts & Donations",
                    sub = listOf("Gifts", "Charity", "Family Support")
                ),
                Category(main = "Clothing", sub = listOf("Shoes", "Laundry", "Casual Wear")),
                Category(
                    main = "Work",
                    sub = listOf("Freelance Tools", "Software", "Workspace Rent")
                ),
                Category(
                    main = "Miscellaneous",
                    sub = listOf("Uncategorized", "Unexpected", "Fees")
                )
            )

            categories.forEach { category ->
                database.categoryDao().addCategory(category)
            }

            val items = listOf(
                Item(name = "Century Tuna", mainCategory = "Food", subCategory = "Groceries"),
                Item(name = "Rice", mainCategory = "Food", subCategory = "Groceries"),
                Item(name = "Piattos", mainCategory = "Food", subCategory = "Snacks"),
                Item(name = "Jollibee", mainCategory = "Food", subCategory = "Dining Out"),
                Item(
                    name = "Jeepney Fare",
                    mainCategory = "Transportation",
                    subCategory = "Public Transport"
                ),
                Item(name = "Gasoline", mainCategory = "Transportation", subCategory = "Fuel"),
                Item(
                    name = "Electric Bill",
                    mainCategory = "Utilities",
                    subCategory = "Electricity"
                ),
                Item(name = "Water Bill", mainCategory = "Utilities", subCategory = "Water"),
                Item(name = "Globe Load", mainCategory = "Utilities", subCategory = "Mobile Load"),
                Item(
                    name = "Silka Soap",
                    mainCategory = "Personal Care",
                    subCategory = "Toiletries"
                ),
                Item(name = "Haircut", mainCategory = "Personal Care", subCategory = "Haircut"),
                Item(name = "Ballpen", mainCategory = "Education", subCategory = "School Supplies"),
                Item(name = "Netflix", mainCategory = "Entertainment", subCategory = "Streaming"),
                Item(
                    name = "SkinWhite Lotion",
                    mainCategory = "Personal Care",
                    subCategory = "Skincare"
                ),
                Item(
                    name = "Donation to Church",
                    mainCategory = "Gifts & Donations",
                    subCategory = "Charity"
                ),
                Item(name = "Ketchup", mainCategory = "Food", subCategory = "Groceries"),
                Item(name = "Powder", mainCategory = "Personal Care", subCategory = "Toiletries"),
                Item(
                    name = "In-game Purchase",
                    mainCategory = "Entertainment",
                    subCategory = "Gaming"
                ),
                Item(name = "GOMO Load", mainCategory = "Utilities", subCategory = "Mobile Load"),
                Item(name = "Nuts", mainCategory = "Food", subCategory = "Snacks"),
                Item(name = "Cooking Oil", mainCategory = "Food", subCategory = "Groceries"),
                Item(name = "Onion", mainCategory = "Food", subCategory = "Groceries"),
                Item(name = "Sliced Bread", mainCategory = "Food", subCategory = "Groceries"),

                Item(
                    name = "Groceries (General)",
                    mainCategory = "Food",
                    subCategory = "Groceries"
                ),
                Item(name = "Snacks (General)", mainCategory = "Food", subCategory = "Snacks"),
                Item(
                    name = "Dining Out (General)",
                    mainCategory = "Food",
                    subCategory = "Dining Out"
                ),
                Item(name = "Drinks (General)", mainCategory = "Food", subCategory = "Drinks"),
                Item(
                    name = "Public Transport (General)",
                    mainCategory = "Transportation",
                    subCategory = "Public Transport"
                ),
                Item(
                    name = "Fuel (General)",
                    mainCategory = "Transportation",
                    subCategory = "Fuel"
                ),
                Item(
                    name = "Toll Fees",
                    mainCategory = "Transportation",
                    subCategory = "Toll Fees"
                ),
                Item(name = "Parking", mainCategory = "Transportation", subCategory = "Parking"),
                Item(
                    name = "Electricity (General)",
                    mainCategory = "Utilities",
                    subCategory = "Electricity"
                ),
                Item(name = "Water (General)", mainCategory = "Utilities", subCategory = "Water"),
                Item(
                    name = "Internet (General)",
                    mainCategory = "Utilities",
                    subCategory = "Internet"
                ),
                Item(
                    name = "Mobile Load (General)",
                    mainCategory = "Utilities",
                    subCategory = "Mobile Load"
                ),
                Item(name = "Rent (Monthly)", mainCategory = "Housing", subCategory = "Rent"),
                Item(name = "House Repairs", mainCategory = "Housing", subCategory = "Repairs"),
                Item(name = "Furnishings", mainCategory = "Housing", subCategory = "Furnishings"),
                Item(
                    name = "Medicine (General)",
                    mainCategory = "Health",
                    subCategory = "Medicine"
                ),
                Item(name = "Check-up Fees", mainCategory = "Health", subCategory = "Check-ups"),
                Item(name = "Supplements", mainCategory = "Health", subCategory = "Supplements"),
                Item(
                    name = "Toiletries (General)",
                    mainCategory = "Personal Care",
                    subCategory = "Toiletries"
                ),
                Item(name = "Haircut", mainCategory = "Personal Care", subCategory = "Haircut"),
                Item(
                    name = "Skincare (General)",
                    mainCategory = "Personal Care",
                    subCategory = "Skincare"
                ),
                Item(
                    name = "Streaming Subscription",
                    mainCategory = "Entertainment",
                    subCategory = "Streaming"
                ),
                Item(
                    name = "In-Game Purchase",
                    mainCategory = "Entertainment",
                    subCategory = "Gaming"
                ),
                Item(name = "Movie Ticket", mainCategory = "Entertainment", subCategory = "Movies"),
                Item(
                    name = "Other Subscriptions",
                    mainCategory = "Entertainment",
                    subCategory = "Subscriptions"
                ),
                Item(
                    name = "School Supplies",
                    mainCategory = "Education",
                    subCategory = "School Supplies"
                ),
                Item(name = "Tuition Fee", mainCategory = "Education", subCategory = "Tuition"),
                Item(
                    name = "Online Course Payment",
                    mainCategory = "Education",
                    subCategory = "Online Courses"
                ),
                Item(
                    name = "Emergency Fund",
                    mainCategory = "Savings",
                    subCategory = "Emergency Fund"
                ),
                Item(
                    name = "Investment Deposit",
                    mainCategory = "Savings",
                    subCategory = "Investments"
                ),
                Item(
                    name = "Bank Transfer to Savings",
                    mainCategory = "Savings",
                    subCategory = "Bank Transfer"
                ),
                Item(
                    name = "Gift Purchase",
                    mainCategory = "Gifts & Donations",
                    subCategory = "Gifts"
                ),
                Item(
                    name = "Charity Donation",
                    mainCategory = "Gifts & Donations",
                    subCategory = "Charity"
                ),
                Item(
                    name = "Family Financial Support",
                    mainCategory = "Gifts & Donations",
                    subCategory = "Family Support"
                ),
                Item(name = "Shoes", mainCategory = "Clothing", subCategory = "Shoes"),
                Item(name = "Laundry Payment", mainCategory = "Clothing", subCategory = "Laundry"),
                Item(
                    name = "Casual Clothing",
                    mainCategory = "Clothing",
                    subCategory = "Casual Wear"
                ),
                Item(
                    name = "Freelance Tool",
                    mainCategory = "Work",
                    subCategory = "Freelance Tools"
                ),
                Item(
                    name = "Software Subscription",
                    mainCategory = "Work",
                    subCategory = "Software"
                ),
                Item(
                    name = "Workspace Rental",
                    mainCategory = "Work",
                    subCategory = "Workspace Rent"
                ),
                Item(
                    name = "Uncategorized Expense",
                    mainCategory = "Miscellaneous",
                    subCategory = "Uncategorized"
                ),
                Item(
                    name = "Unexpected Purchase",
                    mainCategory = "Miscellaneous",
                    subCategory = "Unexpected"
                ),
                Item(
                    name = "Bank/Service Fees",
                    mainCategory = "Miscellaneous",
                    subCategory = "Fees"
                )
            )

            items.forEach { item ->
                database.itemDao().addItem(item)
            }
        }
    }
}