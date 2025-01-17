package com.example.shoplister.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoplister.domain.ShopItem.Companion.UNDEFINED_ID

@Entity(tableName = "shop_items")
data class ShopItemDbModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)