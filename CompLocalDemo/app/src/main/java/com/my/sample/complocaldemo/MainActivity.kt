package com.my.sample.complocaldemo

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.my.sample.complocaldemo.ui.theme.CompLocalDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompLocalDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Composable1()
                }
            }
        }
    }
}

/**
 * Composable1
 * Composable Tree Sample
 * State 를 8에만 전달 하고 싶은데, 그럴려면 사용하지 않는 3, 5 에도 전달하여 8로 최종 전달됨
 * 이것을 해결하기 위해서 CompositionLocal 이 역할을 한다.
 * ex) LocalContext.current 도 이런 메카니즘임
 *                       1
 *                   /        \
 *                2             3
 *                4             5
 *                6         7       8
 *
 *  LocalXXXXX = staticCompositionLocalOf : 자주 변경 되지 않는 상태값일때 사용, 아래 차일드 컴포즈블에 모두 상태값 전달하여 recomposition 이 됨
 *  LocalXXXXX = compositionLocalOf : 현재의 상태에 접근하는 Composable 에 대해서만 recomposition 이 일어나므로 자주 상태 변하는 경우 사용
 *  CompositionLocalProvider(LocalXXXXX provides color) 를 통해서 전달
 *  2,4,6 default color blue
 *  3 darkGray in DarkGray otherwise Green
 *  4 에 LocalColor yellow provides 설정
 *  4, 5, 6 yellow
 */
val LocalColor = staticCompositionLocalOf { Color.Blue }

@Composable
fun Composable1() {
    var color = if (isSystemInDarkTheme()) {
        Color.DarkGray
    } else {
        Color.Green
    }

   Column() {
       Composable2()
       CompositionLocalProvider(LocalColor provides color) {
           Composable3()
       }
   }
}

@Composable
fun Composable2() {
    Text(text = "Composable2", modifier = Modifier.background(LocalColor.current))

    Composable4()
}

@Composable
fun Composable3() {
    Text(text = "Composable3", modifier = Modifier.background(LocalColor.current))

    CompositionLocalProvider(LocalColor provides Color.Yellow) {
        Composable5()
    }
}

@Composable
fun Composable4() {
    Text(text = "Composable4", modifier = Modifier.background(LocalColor.current))

    Composable6()
}

@Composable
fun Composable5() {
    Text(text = "Composable5", modifier = Modifier.background(LocalColor.current))

    Composable7()
    Composable8()
}

@Composable
fun Composable6() {
    Text(text = "Composable6", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable7() {
    Text(text = "Composable7", modifier = Modifier.background(LocalColor.current))

}

@Composable
fun Composable8() {
    Text(text = "Composable8", modifier = Modifier.background(LocalColor.current))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompLocalDemoTheme {
        Composable1()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    CompLocalDemoTheme {
        Composable1()
    }
}