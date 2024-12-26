package com.example.shoplister.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplister.R
import com.example.shoplister.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var count = 0

    companion object {
        private const val ITEM_ENABLE = 1
        private const val ITEM_DISABLE = 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ShopItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder, count:${++count}")
        val layout = when(viewType) {
            ITEM_ENABLE -> R.layout.item_shop_enabled
            ITEM_DISABLE -> R.layout.item_shop_disabled
        else -> throw IllegalArgumentException("Invalid")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ShopItemViewHolder,
        position: Int,
    ) {
        val shopItem = shopList[position]
        viewHolder.view.setOnLongClickListener {
            true
        }
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled)
            ITEM_ENABLE
        else
            ITEM_DISABLE
    }

    override fun getItemCount(): Int {
        return shopList.size
    }


    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }
}