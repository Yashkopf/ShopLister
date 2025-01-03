package com.example.shoplister.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplister.R
import com.example.shoplister.domain.DeleteShopItemUseCase
import com.example.shoplister.domain.ShopItem
import com.example.shoplister.domain.ShopListRepository

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder> (
    ShopItemDiffCallback()
) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ShopItemViewHolder {
        val layout = when(viewType) {
            ITEM_ENABLE -> R.layout.item_shop_enabled
            ITEM_DISABLE -> R.layout.item_shop_disabled
        else -> throw RuntimeException ("Invalid viewType $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ShopItemViewHolder,
        position: Int,
    ) {
        val shopItem = getItem(position)
        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled)
            ITEM_ENABLE
        else
            ITEM_DISABLE
    }

    companion object {
        const val ITEM_ENABLE = 1
        const val ITEM_DISABLE = 0
        const val MAX_POOL_SIZE = 15
    }
}
