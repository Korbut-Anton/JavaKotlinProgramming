package calc_gui

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
  val state = remember { AppState() }
  Window(
    onCloseRequest = ::exitApplication,
    state = WindowState(width = 1000.dp, height = 750.dp),
    title = "(＃￣0￣)", resizable = false
  ) {
    processCalcGUI(state = state)
  }
}
