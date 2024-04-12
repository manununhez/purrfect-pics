package com.manuelnunez.apps.core.common.test

import io.mockk.unmockkAll
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * If you use mockks in your test class, you can add this rule to your test class in order to do an
 * unmockkAll after each test execution, This makes the Test class faster.
 *
 * In the future if we use jUnit5 we could mark our classes with @ExtendWith(MockKExtension::class)
 * to achieve the same behaviour
 */
class UnMockkAllRule : AfterEachCallback {

  override fun afterEach(context: ExtensionContext?) {
    unmockkAll()
  }
}
