package com.example.androiddevchallenge.model

import java.time.Duration

data class TimerModel(
    val timeDuration: Duration = Duration.ofSeconds(30),
    val remainingTime: Long = timeDuration.toMillis(),
    val status: Status = Status.STARTED,
    val toggle: ButtonState = ButtonState.START
)

enum class Status {
    STARTED, RUNNING, FINISHED
}

enum class ButtonState {
    START, PAUSE, RESUME
}