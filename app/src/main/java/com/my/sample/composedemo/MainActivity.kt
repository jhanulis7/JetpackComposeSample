package com.my.sample.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my.sample.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoScreen() {
    var sliderPosition by remember { mutableStateOf(20f) }

    val handlePositionChange = { position: Float ->
        sliderPosition = position
        println("position: $position")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        DemoText(
            message = "Welcome to Compose", fontSize = 20f
        )

        Spacer(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Red)
        )

        DemoSlider(
            position = sliderPosition, onPositionChange = handlePositionChange
        )

        DemoFontSizeText(position = sliderPosition)

        Divider(
            modifier = Modifier.padding(20.dp),
            color = Color.DarkGray
        )

        DemoSwitch()
    }
}

@Composable
fun DemoText(message: String, fontSize: Float) {
    Text(
        text = message, fontSize = fontSize.sp, fontWeight = FontWeight.Bold
    )
}

@Composable
fun DemoSlider(position: Float, onPositionChange: (Float) -> Unit) {
    Slider(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        value = position,
        valueRange = 20f..40f,
        onValueChange = onPositionChange
    )
}

@Composable
fun DemoFontSizeText(position: Float) {
    Text(
        style = MaterialTheme.typography.h2, text = "${position.toInt()}sp", fontSize = position.sp
    )
}

@Composable
fun DemoSwitch(defaultChecked: Boolean = false) {
    val checked = remember {
        mutableStateOf(defaultChecked)
    }
    val onCheckedChanged = { check: Boolean ->
        println("checked$checked")
        checked.value = check
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Switch(
            checked = checked.value,
            onCheckedChange = onCheckedChanged
        )
        Text(text = if(checked.value) "On" else "Off")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        DemoScreen()
    }
}