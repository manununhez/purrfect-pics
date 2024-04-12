package com.manuelnunez.apps

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(), ImageLoaderFactory {
  override fun newImageLoader() =
      ImageLoader(this)
          .newBuilder()
          .memoryCachePolicy(CachePolicy.ENABLED)
          .memoryCache {
            MemoryCache.Builder(this)
                .maxSizePercent(0.1) // up to 10% of remaining memory
                .strongReferencesEnabled(true)
                .build()
          }
          .diskCachePolicy(CachePolicy.ENABLED)
          .diskCache {
            DiskCache.Builder()
                .maxSizePercent(0.03) // up to 3% of remaining memory
                .directory(cacheDir)
                .build()
          }
          .logger(DebugLogger())
          .build()
}
