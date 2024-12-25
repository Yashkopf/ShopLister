package com.example.shoplister.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplister.data.ShopListRepositoryImpl
import com.example.shoplister.domain.DeleteShopItemUseCase
import com.example.shoplister.domain.EditShopItemUseCase
import com.example.shoplister.domain.GetShopListUseCase
import com.example.shoplister.domain.ShopItem


class MainViewModel: ViewModel() {

    val repository = ShopListRepositoryImpl

    val getShopListUseCase = GetShopListUseCase(repository)
    val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    val editShopItemUseCase = EditShopItemUseCase (repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}