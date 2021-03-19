package com.example.androiddevchallenge.model

import java.time.Duration

data class TimerModel(
    val timeDuration: Duration = Duration.ofSeconds(30),
    val secondsLeft : Long = 0L,
    val status: Status = Status.STARTED
)

enum class Status {
    STARTED, RUNNING, FINISHED
}