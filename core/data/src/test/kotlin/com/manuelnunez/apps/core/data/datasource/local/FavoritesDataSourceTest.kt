package com.manuelnunez.apps.core.data.datasource.local

import androidx.datastore.core.DataStore
import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.core.datastore.proto.ItemList
import com.manuelnunez.apps.core.domain.model.Item
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class FavoritesDataSourceTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  val dataStore: DataStore<ItemList> = mockk(relaxed = true)
  val dataSource = FavoritesDataSource(dataStore)

  @Test
  fun `addItemToFavorites adds new item to favorites`() =
      mockkAllExtension.runTest {
        val newItem = Item("1", "image-url", "thumbnail-url", "description")

        // When
        dataSource.addItemToFavorites(newItem)

        // Then
        dataSource.favorites.collect { assertTrue(it.any { it.photoId == newItem.photoId }) }
        coVerify(exactly = 1) { dataStore.updateData(any()) }
      }

  @Test
  fun `remoteItemFromFavorites remove item from favorites`() =
      mockkAllExtension.runTest {
        val removeThisItem = Item("1", "image-url", "thumbnail-url", "description")

        // When
        dataSource.addItemToFavorites(removeThisItem)
        dataSource.removeItemFromFavorites(removeThisItem.photoId)

        // Then
        dataSource.favorites.collect {
          assertTrue(it.none { it.photoId == removeThisItem.photoId })
        }
        coVerify(exactly = 2) { dataStore.updateData(any()) } // one add, one remove
      }
}
