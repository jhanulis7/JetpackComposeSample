package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.customlayout.ui.theme.CustomLayoutTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutTheme {
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
    Column() {
        CustomLayoutExample()

        Divider(color = Color.DarkGray, modifier = Modifier.padding(20.dp))

        CustomLayoutExampleSolution()

        Divider(color = Color.DarkGray, modifier = Modifier.padding(20.dp))

        CustomLayoutFractionSolution()
    }
}

@Composable
fun ColorBox(modifier: Modifier) {
    Box(
        Modifier
            .padding(5.dp)
            .size(width = 100.dp, height = 20.dp)
            .then(modifier))
}

@Composable
fun CustomLayoutExample() {
    //자식 ColorBox 는 부모의 Box 왼쪽 상단 0,0 기준으로 렌더링
    Box(modifier = Modifier
        .size(240.dp, 160.dp)
        .background(Color.Gray)) {
        ColorBox(modifier = Modifier.background(Color.Green))
    }
}

//Modifier 가 호출될때마다 자식을 측정하는 규칙이 적용이된다.
@Composable
fun Modifier.exampleLayout(x: Int, y: Int) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints) // 부모로부터 자식의 크기, 위치를 전달 받음

    //부모로부터 받은 자식의 기준 크기와 위치를 변경 하고 싶은 x, y 로 자식의 위치를 custom 한다.
    layout(placeable.width, placeable.height){
        placeable.placeRelative(x, y)
    }
}

// fraction , guideline 처럼 활용 하는 방법
@Composable
fun Modifier.exampleFractionLayout(fraction: Float) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints) // 부모로부터 자식의 크기, 위치를 전달 받음

    //guideline 을 세로로 기준으로 함
    // guideline 기준으로 그려주는거니, 정렬선(fraction) 기준으로 왼쪽으로 옮겨주는거와 같음 따라서 - 처리.
    val x = -(placeable.width * fraction).roundToInt()

    //부모로부터 받은 자식의 기준 크기와 위치를 변경 하고 싶은 x, y 로 자식의 위치를 custom 한다.
    layout(placeable.width, placeable.height){
        placeable.placeRelative(x = x, y = 0)
    }
}

@Composable
fun CustomLayoutExampleSolution() {
    //자식 ColorBox 는 부모의 Box 왼쪽 상단 0,0 에서 90, 50 으로 렌더링
    Box(modifier = Modifier
        .size(240.dp, 160.dp)
        .background(Color.Gray)) {
        ColorBox(modifier = Modifier
            .exampleLayout(90, 50)
            .background(Color.Green))
    }
}

@Composable
fun CustomLayoutFractionSolution() {
    //자식 ColorBox 는 부모의 Box 왼쪽 상단 0,0 에서 90, 50 으로 렌더링
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(240.dp, 160.dp)
            .background(Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .background(Color.Red)
        ) {
            ColorBox(modifier = Modifier
                .exampleFractionLayout(0f)
                .background(Color.Blue))
            ColorBox(modifier = Modifier
                .exampleFractionLayout(0.25f)
                .background(Color.Cyan))
            ColorBox(modifier = Modifier
                .exampleFractionLayout(0.5f)
                .background(Color.Yellow))
            ColorBox(modifier = Modifier
                .exampleFractionLayout(0.0f)
                .background(Color.Magenta))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomLayoutTheme {
        MainScreen()
    }
}