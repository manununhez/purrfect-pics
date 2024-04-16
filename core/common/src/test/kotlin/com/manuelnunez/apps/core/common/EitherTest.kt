package com.manuelnunez.apps.core.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EitherTest {

  @Test
  fun `test fold with success`() {
    val successData = 42
    val successValue: Either<Int, String> = eitherSuccess(successData)
    val result = successValue.fold(success = { "Success: $it" }, error = { "Error: $it" })

    assertEquals(Either.Success(successData), successValue)
    assertEquals("Success: $successData", result)
  }

  @Test
  fun `test fold with error`() {
    val messageError = "An error occurred"
    val errorValue: Either<Int, String> = eitherError(messageError)
    val result = errorValue.fold(success = { "Success: $it" }, error = { "Error: $it" })

    assertEquals(Either.Error(messageError), errorValue)
    assertEquals(messageError, result)
  }
}
