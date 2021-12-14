package calc_gui

import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester

@Stable
class AppState {
  fun enterCharacterByButton(character: Char) {
    mainTextFieldFocusRequester.requestFocus()
    inputExpression = inputExpression.substring(0, cursorIndex) +
            character + inputExpression.substring(cursorIndex)
    cursorIndex++
  }

  var inputExpression by mutableStateOf("")
  var resultStr by mutableStateOf("")
  var depthStr by mutableStateOf("")
  var debugRepresentationStr by mutableStateOf("")
  var cursorIndex by mutableStateOf(0)
  var listWithVarsInitInfo: List<String> by mutableStateOf(List(15) { "" })
  val mainTextFieldFocusRequester = FocusRequester()
  val mapForVariables = HashMap<String, Double>()
}
