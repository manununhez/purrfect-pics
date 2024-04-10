package com.manuelnunez.apps.core.domain.network

import com.manuelnunez.apps.core.domain.exception.NetworkException

interface NetworkManager {
  /**
   * Checks if Internet is available or throws a [NetworkException]
   *
   * @throws NetworkException when internet is not available
   */
  @Throws(NetworkException::class) fun checkConnectivity()

  /** Returns if the active network is TYPE_WIFI. If there is no internet will throw a [ ] */
  @Throws(NetworkException::class) fun checkWifi(): Boolean
}
