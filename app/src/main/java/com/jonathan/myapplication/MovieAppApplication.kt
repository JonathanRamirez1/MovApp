package com.jonathan.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Se inicializa esta app primero
@HiltAndroidApp
class MovieAppApplication: Application()