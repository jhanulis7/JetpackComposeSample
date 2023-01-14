package com.example.slotapidemo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slotapidemo.ui.theme.SlotApiDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlotApiDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    SlotDemo {
                        SlotButton()
                    }
                }
            }
        }
    }
}

@Composable
fun SlotButton() {
    val context: Context = LocalContext.current
    Button(onClick = { Toast.makeText(context, "click", Toast.LENGTH_SHORT).show() }) {
        Text(text = "중간에 Composable 넣고 사용자 버튼 인터페이스 제공")
    }
}

@Composable
fun SlotDemo(slotComposable: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Top Text")
        Spacer(modifier = Modifier.height(30.dp))
        slotComposable()
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Bottom Text")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SlotApiDemoTheme {
        SlotDemo(slotComposable = { SlotButton() })
    }
}