package com.example.androiddevchallenge.model

import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Duration

class TimerViewModel : ViewModel() {

    private val _viewState = MutableLiveData<TimerModel>()
    val viewState: LiveData<TimerModel> = _viewState

    var countDown: CountDownTimer? = null

    init {
        startTime()
    }


    private fun startTime() {
        countDown = object : CountDownTimer(30000, 10) {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTick(seconds: Long) {
                _viewState.value = TimerModel(Duration.ofMillis(seconds), Status.STARTED)
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFinish() {
                _viewState.value = _viewState.value!!.copy(
                    timeDuration = Duration.ZERO,
                    status = Status.FINISHED
                )
            }

        }
        countDown?.start()
    }

    fun pauseTimer() {
        countDown?.cancel()
    }

    fun stopTimer() {
        countDown?.cancel()
    }

    fun resumeTimer() {
        countDown?.start()
    }


}