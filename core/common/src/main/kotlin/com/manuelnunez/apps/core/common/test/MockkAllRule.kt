package com.manuelnunez.apps.core.common.test

import com.manuelnunez.apps.core.common.dispatcher.CoroutineDispatcherProvider
import io.mockk.unmockkAll
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

@ExperimentalCoroutinesApi
class MockkAllRule : BeforeEachCallback, AfterEachCallback {

  private val testCoroutinesDispatcher = StandardTestDispatcher()
  private val testScope = TestScope(testCoroutinesDispatcher)

  val testCoroutineDispatcherProvider =
      object : CoroutineDispatcherProvider {
        override fun default(): CoroutineDispatcher = testCoroutinesDispatcher

        override fun io(): CoroutineDispatcher = testCoroutinesDispatcher

        override fun main(): CoroutineDispatcher = testCoroutinesDispatcher

        override fun unconfined(): CoroutineDispatcher = testCoroutinesDispatcher
      }

  override fun beforeEach(context: ExtensionContext?) {
    Dispatchers.setMain(testCoroutinesDispatcher)
  }

  override fun afterEach(context: ExtensionContext?) {
    Dispatchers.resetMain()
  }

  fun runTest(testBody: suspend TestScope.() -> Unit) = testScope.runTest(testBody = testBody)
}

class UnMockkAllRule : AfterEachCallback {

  override fun afterEach(context: ExtensionContext?) {
    unmockkAll()
  }
}
