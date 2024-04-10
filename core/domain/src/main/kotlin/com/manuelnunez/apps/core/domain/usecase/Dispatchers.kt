package com.manuelnunez.apps.core.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

  fun main(): CoroutineDispatcher = Dispatchers.Main

  fun default(): CoroutineDispatcher = Dispatchers.Default

  fun io(): CoroutineDispatcher = Dispatchers.IO

  fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}
