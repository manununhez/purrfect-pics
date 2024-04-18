package com.manuelnunez.apps.core.data.datasource.local

import androidx.datastore.core.DataStore
import com.manuelnunez.apps.core.datastore.proto.Item
import com.manuelnunez.apps.core.datastore.proto.ItemList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.manuelnunez.apps.core.domain.model.Item as ItemModel

class FavoritesDataSource @Inject constructor(private val itemDataStore: DataStore<ItemList>) {
  val favorites: Flow<List<ItemModel>> =
      itemDataStore.data.map {
        it.itemsList.map { item ->
          ItemModel(item.photoId, item.imageUrl, item.thumbnailUrl, item.description)
        }
      }

  suspend fun addItemToFavorites(newItem: ItemModel) {
    itemDataStore.updateData { itemList ->
      val updatedItems =
          itemList
              .toBuilder()
              .addItems(
                  Item.newBuilder()
                      .setPhotoId(newItem.photoId)
                      .setImageUrl(newItem.imageUrl)
                      .setThumbnailUrl(newItem.thumbnailUrl)
                      .setDescription(newItem.description)
                      .build())
              .build()
      updatedItems
    }
  }

  suspend fun removeItemFromFavorites(itemPhotoId: String) {
    itemDataStore.updateData { itemList ->
      val updatedItems =
          itemList
              .toBuilder()
              .removeItems(itemList.itemsList.indexOfFirst { it.photoId == itemPhotoId })
              .build()
      updatedItems
    }
  }
}
