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
                        sub = listOf(
                            "Fast Food", "Snacks", "Drinks", "Restaurants", "Cafes",
                            "Meal Prep", "Street Food", "Desserts", "Fruits",
                            "Vegetables", "Meat & Poultry", "Seafood", "Dairy Products",
                            "Baking Supplies", "Instant Food"
                        )
                    ),
                Category(
                    main = "Cosmetics",
                    sub = listOf(
                        "Skincare", "Makeup", "Cosmetic Tools"
                    )
                ),
                Category(
                        main = "Household",
                        sub = listOf(
                            "Cleaning Supplies", "Laundry Products", "Toilet Paper", "Trash Bags",
                            "Air Fresheners", "Disinfectants", "Kitchen Supplies",
                            "Light Bulbs", "Batteries", "Pest Control",
                            "Storage", "Furniture", "Repair Tools", "Bedding", "Curtains", "Decor"
                        )
                    ),
                Category(
                    main = "Personal Care",
                    sub = listOf(
                        "Toiletries", "Hair Care", "Skin Care", "Shaving", "Hygiene", "Sanitary Needs"
                    )
                ),
                Category(
                    main = "Utilities",
                    sub = listOf(
                        "Electricity", "Water", "Gas", "Internet", "Mobile Plan", "Cable TV",
                        "Trash Collection", "Sewage", "Landline", "Security System",
                        "HOA Fees", "Maintenance Fees", "Generator Fuel", "Prepaid Load", "Service Fees"
                    )
                ),
                Category(
                    main = "Electronics",
                    sub = listOf(
                        "PC", "Laptop", "Monitor", "Printer", "PC Components", "Cables & Peripherals",
                        "Storage Devices", "Power Supply", "Cooling", "UPS"
                    )
                ),
                Category(
                    main = "Gadgets",
                    sub = listOf(
                        "Smartphone", "Tablet", "Smartwatch", "Wearables", "Portable Console", "Camera", "Repairs"
                    )
                ),
                Category(
                    main = "Appliances",
                    sub = listOf(
                        "Kitchen", "Laundry", "Cooling", "Heating", "Cleaning", "Small Appliances"
                    )
                ),
                Category(
                    main = "Tech Accessories",
                    sub = listOf(
                        "Audio", "Chargers", "Power Banks", "Screen Protectors", "Cases & Mounts",
                        "USB Devices", "Webcams", "Stylus", "Storage Media"
                    )
                ),
                Category(
                    main = "Transportation",
                    sub = listOf("Public Transport", "Fuel", "Toll Fees", "Parking")
                ),
                Category(
                    main = "Housing",
                    sub = listOf("Rent", "Repairs", "Furnishings")
                ),
                Category(
                    main = "Health",
                    sub = listOf("Medicine", "Check-ups", "Supplements")
                ),
                Category(
                    main = "Entertainment",
                    sub = listOf("Streaming", "Gaming", "Movies", "Subscriptions")
                ),
                Category(
                    main = "Education",
                    sub = listOf("Supplies", "Tuition", "Online Courses")
                ),
                Category(
                    main = "Savings",
                    sub = listOf("Emergency Fund", "Investments", "Bank Transfer")
                ),
                Category(
                    main = "Gifts & Donations",
                    sub = listOf("Gifts", "Charity", "Family Support")
                ),
                Category(
                    main = "Clothing",
                    sub = listOf("Shoes", "Laundry", "Casual Wear")
                ),
                Category(
                    main = "Work",
                    sub = listOf("Tools", "Software", "Workspace Rent")
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
                Item(name = "Jollibee", mainCategory = "Food", subCategory = "Fast Food"),
                Item(name = "Piattos", mainCategory = "Food", subCategory = "Snacks"),
                Item(name = "Coke", mainCategory = "Food", subCategory = "Drinks"),
                Item(name = "Mang Inasal", mainCategory = "Food", subCategory = "Restaurants"),
                Item(name = "Starbucks", mainCategory = "Food", subCategory = "Cafes"),
                Item(name = "Tupperware Meals", mainCategory = "Food", subCategory = "Meal Prep"),
                Item(name = "Fishball", mainCategory = "Food", subCategory = "Street Food"),
                Item(name = "Ice Cream", mainCategory = "Food", subCategory = "Desserts"),
                Item(name = "Banana", mainCategory = "Food", subCategory = "Fruits"),
                Item(name = "Cabbage", mainCategory = "Food", subCategory = "Vegetables"),
                Item(name = "Chicken", mainCategory = "Food", subCategory = "Meat & Poultry"),
                Item(name = "Shrimp", mainCategory = "Food", subCategory = "Seafood"),
                Item(name = "Milk", mainCategory = "Food", subCategory = "Dairy Products"),
                Item(name = "Flour", mainCategory = "Food", subCategory = "Baking Supplies"),
                Item(name = "Cup Noodles", mainCategory = "Food", subCategory = "Instant Food"),
                Item(name = "Moisturizer", mainCategory = "Cosmetics", subCategory = "Skincare"),
                Item(name = "Foundation", mainCategory = "Cosmetics", subCategory = "Makeup"),
                Item(name = "Brush Set", mainCategory = "Cosmetics", subCategory = "Cosmetic Tools"),
                Item(name = "Zonrox", mainCategory = "Household", subCategory = "Cleaning Supplies"),
                Item(name = "Ariel", mainCategory = "Household", subCategory = "Laundry Products"),
                Item(name = "Kleenex", mainCategory = "Household", subCategory = "Toilet Paper"),
                Item(name = "Trash Bags", mainCategory = "Household", subCategory = "Trash Bags"),
                Item(name = "Glade", mainCategory = "Household", subCategory = "Air Fresheners"),
                Item(name = "Lysol", mainCategory = "Household", subCategory = "Disinfectants"),
                Item(name = "Sponge", mainCategory = "Household", subCategory = "Kitchen Supplies"),
                Item(name = "LED Bulb", mainCategory = "Household", subCategory = "Light Bulbs"),
                Item(name = "AA Battery", mainCategory = "Household", subCategory = "Batteries"),
                Item(name = "Baygon", mainCategory = "Household", subCategory = "Pest Control"),
                Item(name = "Organizer Bin", mainCategory = "Household", subCategory = "Storage"),
                Item(name = "Chair", mainCategory = "Household", subCategory = "Furniture"),
                Item(name = "Hammer", mainCategory = "Household", subCategory = "Repair Tools"),
                Item(name = "Bedsheet", mainCategory = "Household", subCategory = "Bedding"),
                Item(name = "Curtain", mainCategory = "Household", subCategory = "Curtains"),
                Item(name = "Wall Frame", mainCategory = "Household", subCategory = "Decor"),
                Item(name = "Toothbrush", mainCategory = "Personal Care", subCategory = "Toiletries"),
                Item(name = "Shampoo", mainCategory = "Personal Care", subCategory = "Hair Care"),
                Item(name = "Lotion", mainCategory = "Personal Care", subCategory = "Skin Care"),
                Item(name = "Razor", mainCategory = "Personal Care", subCategory = "Shaving"),
                Item(name = "Deodorant", mainCategory = "Personal Care", subCategory = "Hygiene"),
                Item(name = "Sanitary Pad", mainCategory = "Personal Care", subCategory = "Sanitary Needs"),
                Item(name = "Electric Bill", mainCategory = "Utilities", subCategory = "Electricity"),
                Item(name = "Water Bill", mainCategory = "Utilities", subCategory = "Water"),
                Item(name = "Tank Refill", mainCategory = "Utilities", subCategory = "Gas"),
                Item(name = "PLDT", mainCategory = "Utilities", subCategory = "Internet"),
                Item(name = "Globe Postpaid", mainCategory = "Utilities", subCategory = "Mobile Plan"),
                Item(name = "Cignal", mainCategory = "Utilities", subCategory = "Cable TV"),
                Item(name = "Garbage Fee", mainCategory = "Utilities", subCategory = "Trash Collection"),
                Item(name = "Sewer Bill", mainCategory = "Utilities", subCategory = "Sewage"),
                Item(name = "Landline", mainCategory = "Utilities", subCategory = "Landline"),
                Item(name = "CCTV Service", mainCategory = "Utilities", subCategory = "Security System"),
                Item(name = "HOA Fee", mainCategory = "Utilities", subCategory = "HOA Fees"),
                Item(name = "Building Maintenance", mainCategory = "Utilities", subCategory = "Maintenance Fees"),
                Item(name = "Diesel for Genset", mainCategory = "Utilities", subCategory = "Generator Fuel"),
                Item(name = "Smart Load", mainCategory = "Utilities", subCategory = "Prepaid Load"),
                Item(name = "Service Charge", mainCategory = "Utilities", subCategory = "Service Fees"),
                Item(name = "Custom PC", mainCategory = "Electronics", subCategory = "PC"),
                Item(name = "Lenovo Laptop", mainCategory = "Electronics", subCategory = "Laptop"),
                Item(name = "AOC Monitor", mainCategory = "Electronics", subCategory = "Monitor"),
                Item(name = "Canon Printer", mainCategory = "Electronics", subCategory = "Printer"),
                Item(name = "RTX 3060", mainCategory = "Electronics", subCategory = "PC Components"),
                Item(name = "HDMI Cable", mainCategory = "Electronics", subCategory = "Cables & Peripherals"),
                Item(name = "Flash Drive", mainCategory = "Electronics", subCategory = "Storage Devices"),
                Item(name = "PSU", mainCategory = "Electronics", subCategory = "Power Supply"),
                Item(name = "CPU Fan", mainCategory = "Electronics", subCategory = "Cooling"),
                Item(name = "UPS", mainCategory = "Electronics", subCategory = "UPS"),
                Item(name = "iPhone", mainCategory = "Gadgets", subCategory = "Smartphone"),
                Item(name = "iPad", mainCategory = "Gadgets", subCategory = "Tablet"),
                Item(name = "Fitbit", mainCategory = "Gadgets", subCategory = "Smartwatch"),
                Item(name = "VR Headset", mainCategory = "Gadgets", subCategory = "Wearables"),
                Item(name = "Nintendo Switch", mainCategory = "Gadgets", subCategory = "Portable Console"),
                Item(name = "GoPro", mainCategory = "Gadgets", subCategory = "Camera"),
                Item(name = "Phone Repair", mainCategory = "Gadgets", subCategory = "Repairs"),
                Item(name = "Microwave", mainCategory = "Appliances", subCategory = "Kitchen"),
                Item(name = "Washing Machine", mainCategory = "Appliances", subCategory = "Laundry"),
                Item(name = "Electric Fan", mainCategory = "Appliances", subCategory = "Cooling"),
                Item(name = "Heater", mainCategory = "Appliances", subCategory = "Heating"),
                Item(name = "Vacuum Cleaner", mainCategory = "Appliances", subCategory = "Cleaning"),
                Item(name = "Rice Cooker", mainCategory = "Appliances", subCategory = "Small Appliances"),
                Item(name = "Headphones", mainCategory = "Tech Accessories", subCategory = "Audio"),
                Item(name = "Phone Charger", mainCategory = "Tech Accessories", subCategory = "Chargers"),
                Item(name = "Anker Power Bank", mainCategory = "Tech Accessories", subCategory = "Power Banks"),
                Item(name = "Tempered Glass", mainCategory = "Tech Accessories", subCategory = "Screen Protectors"),
                Item(name = "Phone Case", mainCategory = "Tech Accessories", subCategory = "Cases & Mounts"),
                Item(name = "USB Hub", mainCategory = "Tech Accessories", subCategory = "USB Devices"),
                Item(name = "Logitech Webcam", mainCategory = "Tech Accessories", subCategory = "Webcams"),
                Item(name = "Stylus Pen", mainCategory = "Tech Accessories", subCategory = "Stylus"),
                Item(name = "SD Card", mainCategory = "Tech Accessories", subCategory = "Storage Media"),
                Item(name = "Chili", mainCategory = "Food", subCategory = "Vegetables"),
                Item(name = "Ice", mainCategory = "Food", subCategory = "Drinks"),
                Item(name = "Tang", mainCategory = "Food", subCategory = "Drinks"),
                Item(name = "Nestea", mainCategory = "Food", subCategory = "Drinks"),
                Item(name = "Mongo", mainCategory = "Food", subCategory = "Vegetables"),
                Item(name = "Cheese Whiz", mainCategory = "Food", subCategory = "Dairy Products"),
                Item(name = "Egg", mainCategory = "Food", subCategory = "Meat & Poultry"),
                Item(name = "Sliced Bread", mainCategory = "Food", subCategory = "Groceries"),
                Item(name = "Aluminium Foil", mainCategory = "Household", subCategory = "Kitchen Supplies"),
                Item(name = "Steamer", mainCategory = "Appliances", subCategory = "Kitchen"),
                Item(name = "Shoe", mainCategory = "Clothing", subCategory = "Shoes"),
                Item(name = "GOMO Load", mainCategory = "Utilities", subCategory = "Prepaid Load"),
                Item(name = "Load", mainCategory = "Utilities", subCategory = "Prepaid Load"),
                Item(name = "Laptop Stand", mainCategory = "Tech Accessories", subCategory = "Cables & Peripherals"),
                Item(name = "Toothpick", mainCategory = "Household", subCategory = "Kitchen Supplies"),
                Item(name = "Bottled Water", mainCategory = "Food", subCategory = "Drinks"),
                Item(name = "Dried Fish", mainCategory = "Food", subCategory = "Seafood"),
                Item(name = "Pancit Canton", mainCategory = "Food", subCategory = "Instant Food"),
                Item(name = "Lucky Me Noodles", mainCategory = "Food", subCategory = "Instant Food")
            )

            items.forEach { item ->
                database.itemDao().addItem(item)
            }
        }
    }
}