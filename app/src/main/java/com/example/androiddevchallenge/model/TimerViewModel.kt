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
        _viewState.value = TimerModel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startTime(duration: Duration) {
        countDown = object : CountDownTimer(duration.toMillis(), 10) {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTick(seconds: Long) {
                _viewState.value = TimerModel(
                    timeDuration = Duration.ofMillis(seconds),
                    status = Status.RUNNING,
                    toggle = ButtonState.PAUSE
                )
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFinish() {
                _viewState.value = _viewState.value!!.copy(
                    timeDuration = Duration.ZERO,
                    status = Status.FINISHED,
                    toggle = ButtonState.START
                )
            }

        }
        countDown?.start()
    }

    private fun pauseTimer() {
        countDown?.cancel()
        _viewState.value = _viewState.value!!.copy(
            status = Status.STARTED,
            toggle = ButtonState.RESUME
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetTimer() {
        countDown?.cancel()
        _viewState.value = _viewState.value!!.copy(
            status = Status.STARTED,
            timeDuration = Duration.ofMillis(30000),
            toggle = ButtonState.START
        )
    }

    fun buttonSelection() {
        val state = _viewState.value

        when (state?.status) {
            Status.STARTED -> {
                startTime(state.timeDuration)
            }
            Status.RUNNING -> {
                pauseTimer()
            }
            Status.FINISHED -> {
                resetTimer()
            }
        }

    }
}