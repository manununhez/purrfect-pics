package com.manuelnunez.apps.core.services.interceptor

import com.manuelnunez.apps.core.services.BuildConfig
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class PexelsKeyAuthenticator : Authenticator {
  /**
   * Authenticator for when the authToken need to be refresh and updated every time we get a 401
   * error code
   */
  @Throws(IOException::class)
  override fun authenticate(route: Route?, response: Response): Request? {
    try {
      return response.request
          .newBuilder()
          .addHeader("Authorization", BuildConfig.PEXELS_API_KEY)
          .build()
    } catch (_: Exception) {}
    return null
  }
}
