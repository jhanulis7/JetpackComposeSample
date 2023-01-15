package com.example.slotapidemo

import android.content.Context
import android.icu.text.CaseMap.Title
import android.media.midi.MidiOutputPort
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
                    Column {
                        SlotDemo {
                            SlotButton()
                        }
                        Divider(
                            modifier = Modifier.padding(10.dp),
                            color = Color.DarkGray,
                            thickness = 5.dp
                        )
                        MainScreen()
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

@Composable
fun MainScreen() {
    var linearSelected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(true) }
    val onLinearClick = { value: Boolean ->
        linearSelected = value
    }
    val onTitleClick = { value: Boolean ->
        imageSelected = value
    }

    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onLinearClick = onLinearClick,
        onTitleClick = onTitleClick,
        progressContent = {
            if (linearSelected) {
                LinearProgressIndicator(
                    modifier = Modifier.height(40.dp),
                    color = Color.Green,
                    backgroundColor = Color.Gray
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    strokeWidth = 18.dp,
                    color = Color.Green
                )
            }
        }
    ) {
        if (imageSelected) {
            TitleImage(resId = R.drawable.ic_baseline_cloud_download_24)
        } else {
            Text(
                text = "Downloading",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(30.dp)
            )
        }
    }
}

@Composable
fun ScreenContent(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onLinearClick: (Boolean) -> Unit,
    onTitleClick: (Boolean) -> Unit,
    progressContent: @Composable () -> Unit,
    titleContent: @Composable () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        progressContent()
        titleContent()

        CheckBoxes(
            linearSelected = linearSelected,
            imageSelected = imageSelected,
            onLinearClick = onLinearClick,
            onTitleClick = onTitleClick
        )
    }
}

@Composable
fun CheckBoxes(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onLinearClick: (Boolean) -> Unit,
    onTitleClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = linearSelected,
            onCheckedChange = onLinearClick
        )
        Text("Linear Progress")

        Spacer(modifier = Modifier.width(20.dp))

        Checkbox(
            checked = imageSelected,
            onCheckedChange = onTitleClick
        )
        Text("Image Title")
    }
}

@Composable
fun TitleImage(resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = "title image"
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SlotApiDemoTheme {
        SlotDemo(slotComposable = { SlotButton() })
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    SlotApiDemoTheme {
        MainScreen()
    }
}


@Preview(showBackground = true)
@Composable
fun CheckBoxesPreview() {
    CheckBoxes(
        imageSelected = true,
        linearSelected = true,
        onLinearClick = {},
        onTitleClick = {}
    )
}
