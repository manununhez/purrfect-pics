package com.manuelnunez.apps.core.domain.usecase

import com.manuelnunez.apps.core.common.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class FlowUseCase<T, R>(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) {

  protected open fun dispatcher(): CoroutineContext = coroutineDispatcherProvider.io()

  /** Returns a [Flow] that will be executed in the specified [CoroutineContext]. */
  protected abstract fun execute(input: T): Flow<R>

  /** Prepares and returns the [Flow] with the specified input. */
  fun prepare(input: T): Flow<R> = execute(input).flowOn(coroutineDispatcherProvider.io())
}
