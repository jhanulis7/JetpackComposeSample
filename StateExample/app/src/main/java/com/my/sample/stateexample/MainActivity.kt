package com.my.sample.stateexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.my.sample.stateexample.ui.theme.StateExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoScreen() {
    var textState by rememberSaveable { mutableStateOf("abc") }
    val onTextChanged = { text: String ->
        textState = text
    }

    //자식에게 State 와 Handler 전달 - unidirection data flow
    //부모가 상태를 관리하고, 자식으로부터 이벤트를 받아서 상태변화를 하도록 한다.
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        MyTextField(textState, onTextChanged)

        Spacer(modifier = Modifier.height(50.dp))

        FunctionA()
    }
}

@Composable
fun MyTextField(textState: String, onTextChanged: (String) -> Unit) {
    //자식은 사용자의 이벤트를 부모(DemoScreen)에게 전달하고, 상태값을 바꾼다음 다시 자식으로 recomposition 되도록 한다.
    //자식은 stateless composable 로 만들고 state hoisting 되도록 하여, 부모가 상태관리 하도록 해서 재사용성을 높인다.
    Text(text = "stateful/stateless composable")
    TextField(value = textState, onValueChange = onTextChanged)
}

@Composable
fun FunctionA() {
    var switchedState by remember {
        mutableStateOf(false)
    }
    
    val onSwitchChanged = { onOff: Boolean ->
        switchedState = onOff
    }

    FunctionB(
        switchedState = switchedState,
        onSwitchChanged = onSwitchChanged
    )
}

@Composable
fun FunctionB(switchedState: Boolean, onSwitchChanged: (Boolean) -> Unit) {
    Divider(color = Color.DarkGray, modifier = Modifier.padding(20.dp))

    Text(text = "State hoisting Sample")
    Switch(checked = switchedState, onCheckedChange = onSwitchChanged)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateExampleTheme {
        DemoScreen()
        FunctionA()
    }
}