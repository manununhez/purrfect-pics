package com.manuelnunez.apps.core.services.authenticator

import com.manuelnunez.apps.core.services.BuildConfig
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class KeyAuthenticator : Authenticator {
  /**
   * Authenticator for when the authToken need to be refresh and updated every time we get a 401
   * error code
   */
  @Throws(IOException::class)
  override fun authenticate(route: Route?, response: Response): Request? {
    var requestAvailable: Request? = null
    try {
      requestAvailable =
          response.request
              .newBuilder()
              .addHeader("Authorization", BuildConfig.PEXELS_API_KEY)
              .build()
      return requestAvailable
    } catch (ex: Exception) {}
    return requestAvailable
  }
}
