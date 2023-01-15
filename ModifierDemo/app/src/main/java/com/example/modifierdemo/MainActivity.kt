package com.example.modifierdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modifierdemo.ui.theme.ModifierDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModifierDemoTheme {
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
    //Modifier 는 테두리 배경 크기 핸들러 제스쳐등을 다른 컴포즈블에 전달한다.
    //이때 Modifier 의 순서가 가장 중요하다.
    //Modifier 는 Param 중 1st 선택적 Param 이어야 한다.
    val modifier1 = Modifier //padding 후에 border 색상
        .padding(20.dp)
        .border(
            width = 3.dp,
            color = Color.Red
        )

    val modifier2 = Modifier//border 색상 후에 padding
        .border(
            width = 3.dp,
            color = Color.Red
        )
        .padding(20.dp)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Hello Compose1",
            modifier = modifier1,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Hello Compose2",
            modifier = modifier2,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(color = Color.DarkGray, modifier = Modifier.padding(30.dp))
        CustomImage(
            imageId = R.drawable.ic_tmap,
            modifier = Modifier
                .padding(16.dp)
                .width(270.dp)
                .clip(shape = RoundedCornerShape(30.dp)
            )
        )
    }
}

@Composable
fun CustomImage(
    imageId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = "image",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModifierDemoTheme {
        DemoScreen()
    }
}