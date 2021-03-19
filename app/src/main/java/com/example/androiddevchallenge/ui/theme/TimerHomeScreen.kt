package com.example.androiddevchallenge.ui.theme

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.TimerModel
import com.example.androiddevchallenge.model.TimerViewModel
import com.example.androiddevchallenge.ui.ext.format

@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimerHomeScreen(viewModel: TimerViewModel) {
    val timer by viewModel.viewState.observeAsState(TimerModel())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerHeader()
        TimerTopSection(timer.timeDuration.format())
        TimerButtons(viewModel)
    }
}

@Composable
fun TimerTopSection(time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = time, fontSize = 60.sp)
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerButtons(timerState: TimerViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var visible by remember { mutableStateOf(true) }
        Box {
            if (visible) {
                IconButton(onClick = {
                    timerState.buttonSelection()
                    visible = !visible
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_play),
                        contentDescription = "play button",

                        )
                }

            } else {
                IconButton(onClick = {
                    timerState.buttonSelection()
                    visible = !visible
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_pause),
                        contentDescription = "pause button",

                        )
                }

            }

        }

        IconButton(onClick = { timerState.stopTimer() }) {
            Icon(painter = painterResource(R.drawable.ic_stop), contentDescription = "stop button")
        }
    }
}

@Composable
fun TimerHeader() {
    Text(
        text = "Count Down Timer",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        style = MaterialTheme.typography.h4
    )
}