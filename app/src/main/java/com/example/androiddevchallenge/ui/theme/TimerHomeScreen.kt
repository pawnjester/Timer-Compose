package com.example.androiddevchallenge.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.ButtonState
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
            .fillMaxHeight()
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerHeader()
        Spacer(modifier = Modifier.height(25.dp))
        TimerTopSection(timer.timeDuration.format())
        Spacer(modifier = Modifier.height(25.dp))
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
        Text(text = time, fontSize = 60.sp, color = Color.White)
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerButtons(timerState: TimerViewModel) {
    val toggle by timerState.viewState.observeAsState()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            timerState.resetTimer()
        }) {
            Icon(painter = painterResource(R.drawable.ic_stop), contentDescription = "stop button")
        }

        ButtonLayout(timerState)
    }
}

@Composable
fun ButtonLayout(timerState: TimerViewModel) {
    val toggle by timerState.viewState.observeAsState()
    var text = ""
    var color: Color = MaterialTheme.colors.primaryVariant
    var textColor: Color = Color.White
    when (toggle?.toggle) {
        ButtonState.START -> {
            text = "Start"
            color = MaterialTheme.colors.primaryVariant
            textColor = Color.White
        }
        ButtonState.PAUSE -> {
            text = "Pause"
            color = MaterialTheme.colors.secondary
            textColor = Color.Black
        }
        ButtonState.RESUME -> {
            text = "Resume"
            color = MaterialTheme.colors.secondaryVariant
            textColor = Color.Black
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier
            .clickable {
                timerState.resetTimer()
            }
            .padding(30.dp)
            .size(80.dp)
            .clip(CircleShape)
            .background(Color.DarkGray)
            .fillMaxWidth()) {
            Text(
                text = "Reset", color = Color.White, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
            )
        }

        Box(modifier = Modifier
            .clickable {
                timerState.buttonSelection()
            }
            .padding(10.dp)
            .size(80.dp)
            .clip(CircleShape)
            .background(color)
            .fillMaxWidth()) {
            Text(
                text = text, color = textColor, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun TimerHeader() {
    Text(
        text = "Count Down Timer",
        fontSize = 30.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        style = MaterialTheme.typography.h4
    )
}