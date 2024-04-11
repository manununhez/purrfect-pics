package com.manuelnunez.apps.core.services.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.manuelnunez.apps.core.services.exception.NetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(val context: Context) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    if (!isInternetAvailable(context)) {
      throw NetworkException("Not Available")
    }

    // Proceed with the request if network is available
    return chain.proceed(chain.request())
  }

  private fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      val network = connectivityManager.activeNetwork
      val capabilities = connectivityManager.getNetworkCapabilities(network)
      capabilities != null &&
          (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
              capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    } else {
      @Suppress("DEPRECATION") val networkInfo = connectivityManager.activeNetworkInfo
      networkInfo != null && networkInfo.isConnected
    }
  }
}
