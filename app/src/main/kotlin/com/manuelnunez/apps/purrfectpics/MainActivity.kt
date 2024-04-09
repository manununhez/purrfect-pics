package com.manuelnunez.apps.purrfectpics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.manuelnunez.apps.purrfectpics.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val viewModel: SplashViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    val splashScreen = installSplashScreen()
    super.onCreate(savedInstanceState)

    splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }

    setContent {
      MainTheme {  }
    }
  }
}
