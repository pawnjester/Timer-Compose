package com.example.androiddevchallenge.model

import java.time.Duration

data class TimerModel(
    val timeDuration: Duration = Duration.ofSeconds(10),
    val status: Status = Status.STARTED
)

enum class Status {
    STARTED, PAUSED, FINISHED
}