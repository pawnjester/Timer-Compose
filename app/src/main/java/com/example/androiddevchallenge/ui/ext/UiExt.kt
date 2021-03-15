package com.example.androiddevchallenge.ui.ext

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
fun Duration.format(): String {
    val seconds = abs(seconds)
    val value = String.format(
        "%02d:%02d",
        seconds % 3600 / 60,
        seconds % 60
    )
    return value
}