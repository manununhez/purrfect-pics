package com.manuelnunez.apps.purrfectpics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.manuelnunez.apps.purrfectpics.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MainTheme {  }
    }
  }
}
