# JetpackComposeSample
## Jetpack Compose Study &amp; Demo App
- 핵심만 골라배우는 Jetpack Compose 책을 읽고 샘플코드를 정리해본다.
- Compose 는 Foundation or Layout or Material Design 로 구분할 수 있고 이 사용 방법에 대해서 샘플코드 작성해본다.

## ComposeDemo
- Stateful Slide Composable and handler
  - var slidePosition by remember { mutableStateOf(20f) } 
  - val onSlideChange = { position: Float -> slidePosition = position }
- Stateful Switch Composable and handler
  - val selectedState = remember { mutableStateOf(false) } 
  - val onSwitchChange = { onOff: Boolean -> selectedState.value = onOff }
- Spacer / Text / Divider / Columm Foundation or Layout or Material Design 사용
- <p align="center"><img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/DemoCompose.png" width="30%" height="30%"></p>

## StateExample
- stateful / stateless composable
- state hoisting 
  - Composable Tree 에서 하위로 데이타 단방향으로 상태 및 핸들러를 전달하고, 사용자 인터랙션이 일어나면 핸들러를 통해서 이벤트를 위로 전달함.
  - 핸들러에는 상태값 갱신이 구현되도록 하여, 최상위에서만 상태값을 변하게끔 하도록 하고, 상태가 변하면 아래 Composable 에게 recompostion 되도록 함
- rememberSavalble : onConfiguration 이 되더라도 상태값이 유지되도록 함
  - var slidePosition by rememberSavable { mutableStateOf(20f) } 
- <p align="center"><img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/Hoisting.png" width="30%" height="30%"></p>

## CompLocalDemo
- compositionLocalOf : Composable child tree 에서 사용되어지는 Composable 에서만 사용 가능, 자주 변하는 상태값일때 사용
- staticCompositionLocalOf : Composable child tree 에서 하위 child 에 상태 전달, 항상 전달되지 않도록 static 하게 선언해서 바로 사용하도록함
- CompositionLocalProvider : LocalXXXX provides value 형태로 주입
  - ex) val context:Context = LocalContext.current  <-- 이것도 이것을 통해서 구현되어 어떤 childe Composable 에서 사용 가능
- <p align="center"><img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/CompLocal.png" width="30%" height="30%"></p>

## SlotApiDemo
- Composable 함수를 다른 함수에 넣어주는 방법
- Composable 함수를 받은 Composable 은 런타임중에 동적으로 삽입이 되어, UI 를 구성할 수 있다
- 즉, Slot Api 를 이용한 Composable 은 본질적으로 런타임중에 삽입할 수 있는 하나 이상의 다른 Composable 을 포함하는 사용자 인터페이스 템플릿이다.
- LinearProgressIndicator / CircularProgressIndicator / Image / CheckBoxes / painterResource
- <p align="center"><img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/SlotApi.png" width="30%" height="30%">
  <img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/SlotApi2.png" width="30%" height="30%">
  <img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/SlotApi3.png" width="30%" height="30%"></p>

## ModifierDemo
- Modifier 는 테두리 배경 크기 핸들러 제스쳐등을 다른 컴포즈블에 전달한다.
- 이때 Modifier 의 순서가 가장 중요하다.
  - padding 이후에 border 를 할것인가, border 후에 padding 할것인가.
- Modifier 는 Param 중 1st 선택적 Param 이어야 한다.
  - fun CustomImage(imageId: Int, modifier: Modifier = Modifier)
- Modifer 를 합칠경우, then 을 사용
  - val combined = first.then(second)  
- RoundedCornerShape / clip
- <p align="center"><img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/Modifier.png" width="30%" height="30%"></p>

## LayoutDemo
- Row, Column 사용법 / Box Layout 사용법
- alignByBaseline / alignBy / clip / RoundCorner / CircleShape / CutCornerShape
- <p align="center"><img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/Layout.png" width="30%" height="30%"></p>

## CustomLayoutDemo
- Box, Row, Column 컴포넌트는 모든 자식의 높이와 폭을 측정하고 위치를 계산해 그에 맞게 행열 스택위치를 생성하는 로직을 포함하고 있다. 동일한 기법을 통하여 다양한 고급 레이아웃을 만들수 있다
- fun Modifier.<커스텀 레이아웃이름>(선택적 param) { measurable, constaints -> }
  - measurable : 자식이 배치될 정보
  - constraints : 자식이 사용할 수 있는 최대/최소 widhth/height 
- <p align="center"><img src = "https://github.com/jhanulis7/JetpackComposeSample/blob/main/CustomLayout.png" width="30%" height="30%"></p>
