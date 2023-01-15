package com.example.layoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.layoutdemo.ui.theme.LayoutDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RowColLayoutScreen()
                }
            }
        }
    }
}

@Composable
fun RowColLayoutScreen() {
    Column {
        LayoutDemo1()
        LayoutDemo1Solution() // 중앙 정렬
        Divider(color = Color.DarkGray)
        LayoutDemo2() //마지막 라인에 맞춤
        LayoutDemo2Solution() //1st Line 에 맞춤
    }
}

@Composable
fun LayoutDemo1() {
    Row{
        Text(
            text = "Large Text",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Small Text",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LayoutDemo1Solution() {
    Row{
        Text(
            text = "Large Text",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alignByBaseline()
        )
        Text(
            text = "Small Text",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alignByBaseline()
        )
    }
}

@Composable
fun LayoutDemo2() {
    Row{
        Text(
            text = "Large Text\nMoreText",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alignBy(LastBaseline)
        )
        Text(
            text = "Small Text",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alignByBaseline()
        )
    }
}

@Composable
fun LayoutDemo2Solution() {
    Row{
        Text(
            text = "Large Text\nMoreText",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alignBy(FirstBaseline)
        )
        Text(
            text = "Small Text",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alignByBaseline()
        )
    }
}

@Composable
fun BoxLayoutScreen() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box {
            TextCell("1")
            TextCell("2")
            TextCell("3")
        }

        Box(modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Blue))
        Box(modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Red))
        Box(modifier = Modifier
            .size(50.dp)
            .clip(CutCornerShape(5.dp))
            .background(Color.Blue))
    }
}


@Composable
fun TextCell(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 150
) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 4.dp, color = Color.DarkGray)

    //surface 를 지우면, Text 가 투명이라 1,2,3 이 겹쳐보인다
    //surface 를 사용하면, 불투명으로 바꿔서 3이 덮어버린다.
    Surface() {
        Text(
            text = text,
            modifier = cellModifier.then(modifier),
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }
}

@Preview(showBackground = true)
@Composable
fun RowColLayoutPreview() {
    LayoutDemoTheme {
        RowColLayoutScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun BoxLayoutPreview() {
    LayoutDemoTheme {
        BoxLayoutScreen()
    }
}