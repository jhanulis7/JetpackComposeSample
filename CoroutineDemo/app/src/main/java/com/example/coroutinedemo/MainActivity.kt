package com.example.coroutinedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coroutinedemo.ui.theme.CoroutineDemoTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutineDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch(Dispatchers.Main) { performTask1()}
        coroutineScope.launch(Dispatchers.Main) { performTask2()}
        coroutineScope.launch(Dispatchers.Main) { performSlowTask()}
    }
}


val channel = Channel<Int>()
suspend fun performTask1() {
    (1..6).forEach {
        channel.send(it)
    }
}

suspend fun performTask2() {
    repeat(6) {
        println("Received : $it")
    }
}

suspend fun performSlowTask() {
    println("performSlowTask before......")
    delay(2000)
    println("performSlowTask after......")
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoroutineDemoTheme {
        MainScreen()
    }
}