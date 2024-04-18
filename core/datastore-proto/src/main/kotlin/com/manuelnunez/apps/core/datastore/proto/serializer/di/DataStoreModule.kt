package com.manuelnunez.apps.core.datastore.proto.serializer

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.manuelnunez.apps.core.datastore.proto.ItemList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

  @Provides @Singleton internal fun providesItemListSerializer() = ItemListSerializer()

  @Provides
  @Singleton
  internal fun providesItemListDataStore(
      @ApplicationContext context: Context,
      itemListSerializer: ItemListSerializer,
  ): DataStore<ItemList> =
      DataStoreFactory.create(
          serializer = itemListSerializer,
      ) {
        context.dataStoreFile("favorites.pb")
      }
}
