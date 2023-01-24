package com.example.viewmodeldemo

import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viewmodeldemo.ui.theme.ViewModelDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenSetup()
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(
    //viewModel 을 singleTone 하지 않고, 인자를 받지 않으면 매번 새로운 뷰모델 생성 하게 됨
    //이런 경우, hilt 싱글톤 injection 하던가, MainActivity 에서 viewModel 받도록 한다.
    //또는 자식 composable(subScreen) 에 viewModel 상태와 핸들러를 전달한다. 이번 예제는 이걸로 한다.
    viewModel: TempViewModel = TempViewModel()
) {
    ////Log.d("ViewModel", "ScreenSetup() isFahrenheit: ${viewModel.isFahrenheit}")

    MainScreen(
        isFahrenheit = viewModel.isFahrenheit,
        result = viewModel.result,
        convertTemp = { viewModel.convertTemp(it) },
        switchChange = { viewModel.switchChange() }
    )
}

@Composable
fun MainScreen(
    isFahrenheit: Boolean,
    result: String,
    convertTemp: (String) -> Unit,
    switchChange: () -> Unit
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var textState by remember { mutableStateOf("") }
        //Log.d("ViewModel", "subScreen() Enter!! textState:$textState")

        val onTextChange = { text: String ->
            //Log.d("ViewModel", "subScreen() onTextChange: text:$text")
            textState = text
        }

        //Log.d("ViewModel", "subScreen() isFahrenheit: $isFahrenheit")

        Text("Temperature Converter",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.h4
        )

        InputRow(
            isFahrenheit = isFahrenheit,
            textState = textState,
            onSwitchChange = switchChange,
            onTextChange = onTextChange
        )

        //Log.d("ViewModel", "subScreen() : result:$result")
        Text(
            text = result,
            modifier = Modifier.padding(20.dp)
        )

        //Log.d("ViewModel", "subScreen() : textState:$textState")
        Button(onClick = { convertTemp(textState) }) {
            Text(
                text = "Convert Temperature",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
fun InputRow(
    isFahrenheit: Boolean,
    textState: String,
    onSwitchChange: () -> Unit,
    onTextChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = isFahrenheit,
            onCheckedChange = { onSwitchChange() }
        )

        OutlinedTextField(
            value = textState,
            onValueChange = {
                onTextChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text("Enter Temperature") },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_ac_unit_24),
                    contentDescription = "frost",
                    modifier = Modifier.size(40.dp)
                )
            }
        )

        Crossfade(
            targetState = isFahrenheit,
            animationSpec = tween(2000)
        ) { visible ->
            when (visible) {
                true -> Text(text = "\u2109", style = MaterialTheme.typography.h4)
                false -> Text(text = "\u2103", style = MaterialTheme.typography.h4)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(viewModel: TempViewModel = TempViewModel()) {
    ViewModelDemoTheme {
        MainScreen(
            isFahrenheit = viewModel.isFahrenheit,
            result = viewModel.result,
            convertTemp = { viewModel.convertTemp(it) },
            switchChange = { viewModel.switchChange() }
        )
    }
}
