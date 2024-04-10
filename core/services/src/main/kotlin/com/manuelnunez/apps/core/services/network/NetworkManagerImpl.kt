package com.manuelnunez.apps.core.services.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.manuelnunez.apps.core.domain.exception.NetworkException
import com.manuelnunez.apps.core.domain.network.NetworkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkManagerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    NetworkManager {
  /**
   * Checks if Internet is available or throws a [NetworkException]
   *
   * @throws NetworkException when internet is not available
   */
  @Throws(NetworkException::class)
  override fun checkConnectivity() {
    if (!isInternetAvailable(context)) {
      throw NetworkException("Not Available")
    }
  }

  /**
   * Returns if the active network is [ConnectivityManager.TYPE_WIFI]. If there is no internet will
   * throw a [NetworkException]
   *
   * @throws NetworkException when internet is not available
   */
  @Throws(NetworkException::class)
  override fun checkWifi(): Boolean {
    checkConnectivity()
    return isWifiConnected(context)
  }

  companion object {
    /** Checks if there is any active network */
    private fun isInternetAvailable(context: Context): Boolean {
      val activeNetwork = getActiveNetwork(context)
      return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    /** Checks if the active connection is [ConnectivityManager.TYPE_WIFI] */
    private fun isWifiConnected(context: Context): Boolean {
      val activeNetwork = getActiveNetwork(context)
      return activeNetwork != null && activeNetwork.type == ConnectivityManager.TYPE_WIFI
    }

    /** Get the active [NetworkInfo] */
    private fun getActiveNetwork(context: Context): NetworkInfo? {
      return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)
          ?.activeNetworkInfo
    }
  }
}
