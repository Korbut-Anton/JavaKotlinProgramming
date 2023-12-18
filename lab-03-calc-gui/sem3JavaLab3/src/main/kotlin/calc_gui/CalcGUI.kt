package calc_gui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javalab1gradle.*
import kotlin.Pair

fun parseStrWithValOfVariable(str: String): Pair<String, Double> {
  val strWithoutSpaces = str.replace(" ", "")
  if (!((strWithoutSpaces[0] >= 'a') && (strWithoutSpaces[0] <= 'z') &&
            (strWithoutSpaces[1] == '='))
  ) {
    throw StringParseException(
      "Not available to define the value of variable: incorrect input"
    )
  }
  return Pair(
    strWithoutSpaces[0].toString(), java.lang.Double.valueOf(
      strWithoutSpaces.substring(2, strWithoutSpaces.length)
    )
  )
}

@Composable
fun RowScope.processCalcButtonForInput(
  state: AppState, character: Char, color: Color
) {
  Button(
    onClick = {
      state.enterCharacterByButton(character)
    },
    colors = ButtonDefaults.buttonColors(backgroundColor = color),
    modifier = Modifier.fillMaxSize().weight(1f).padding(all = 4.dp)
  ) {
    Text(text = character.toString())
  }
}

@Composable
fun RowScope.processCalcButtons(state: AppState) {
  Column(modifier = Modifier.fillMaxSize().weight(1f)) {
    Row(modifier = Modifier.fillMaxSize().weight(1f)) {
      for (i in 1..3) {
        processCalcButtonForInput(state, "$i"[0], Color.Blue)
      }
      processCalcButtonForInput(state, '*', CustomColors.Orange)
    }
    Row(modifier = Modifier.fillMaxSize().weight(1f)) {
      for (i in 4..6) {
        processCalcButtonForInput(state, "$i"[0], Color.Blue)
      }
      processCalcButtonForInput(state, '/', CustomColors.Orange)
    }
    Row(modifier = Modifier.fillMaxSize().weight(1f)) {
      for (i in 7..9) {
        processCalcButtonForInput(state, "$i"[0], Color.Blue)
      }
      processCalcButtonForInput(state, '+', CustomColors.Orange)
    }
    Row(modifier = Modifier.fillMaxSize().weight(1f)) {
      processCalcButtonForInput(state, '(', Color.Yellow)
      processCalcButtonForInput(state, '0', Color.Blue)
      processCalcButtonForInput(state, ')', Color.Yellow)
      processCalcButtonForInput(state, '-', CustomColors.Orange)
    }
    Row(modifier = Modifier.fillMaxSize().weight(1f)) {
      processCalcButtonForInput(state, '.', Color.Gray)
      Button(
        onClick = {
          state.mainTextFieldFocusRequester.requestFocus()
          if (state.cursorIndex > 0) {
            state.inputExpression =
              state.inputExpression.substring(0, state.cursorIndex - 1) +
                      state.inputExpression.substring(state.cursorIndex)
            state.cursorIndex--
          }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        modifier = Modifier.fillMaxSize().weight(1f).padding(all = 4.dp)
      ) {
        Text(text = "X")
      }
      Button(
        onClick = {
          state.mainTextFieldFocusRequester.requestFocus()
          if (state.cursorIndex > 0) {
            state.cursorIndex--
          }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier.fillMaxSize().weight(1f).padding(all = 4.dp)
      ) {
        Text(text = "<-")
      }
      Button(
        onClick = {
          state.mainTextFieldFocusRequester.requestFocus()
          if (state.cursorIndex != state.inputExpression.length) {
            state.cursorIndex++
          }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier.fillMaxSize().weight(1f).padding(all = 4.dp)
      ) {
        Text(text = "->")
      }
    }
  }
}

fun processExpression(state: AppState) {
  try {
    val parser = ParserImpl()
    val expression: Expression =
      parser.parseExpression(state.inputExpression)
    for (elem in state.listWithVarsInitInfo) {
      val elemWithoutSpaces = elem.replace(" ", "")
      if (elemWithoutSpaces != "") {
        val varsInitInfo = parseStrWithValOfVariable(elem)
        state.mapForVariables[varsInitInfo.first] = varsInitInfo.second
      }
    }
    state.debugRepresentationStr = "Debug representation: " +
            expression.accept(
              DebugRepresentationExpressionVisitor.INSTANCE
            )
    state.depthStr = "Depth: " + expression.accept(
      ComputeTreeHeightExpressionVisitor.INSTANCE
    ).toString()
    val result = expression.accept(
      ComputeExpressionVisitor(
        state.mapForVariables
      )
    )
    state.resultStr = if (result.isInfinite() || result.isNaN()) {
      "Calculation error"
    } else {
      "Result: $result"
    }
  } catch (ex: Exception) {
    when (ex) {
      is ExpressionCreationException, is ExpressionParseException,
      is NumberFormatException, is StringParseException,
      is NullPointerException -> {
        state.resultStr = ""
        state.depthStr = ""
        state.debugRepresentationStr = ""
      }
      else -> throw ex
    }
  } finally {
    state.mapForVariables.clear()
  }
}

@Composable
fun RowScope.processRightPartOfInterface(state: AppState) {
  Column(modifier = Modifier.fillMaxSize().weight(1f)) {
    Row(modifier = Modifier.fillMaxSize().weight(1.5f)) {
      TextField(
        onValueChange = {
          state.cursorIndex = it.selection.start
          state.inputExpression = it.text
        },
        value = TextFieldValue(
          state.inputExpression,
          TextRange(state.cursorIndex)
        ),
        textStyle = TextStyle(fontSize = 25.sp),
        label = { Text("Enter Expression") },
        singleLine = true,
        modifier = Modifier.fillMaxSize().weight(7f)
          .focusRequester(state.mainTextFieldFocusRequester)
      )
      Button(
        onClick = {
          processExpression(state)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
        modifier = Modifier.fillMaxSize().weight(4f)
      ) {
        Text("Apply")
      }
    }
    LazyColumn(
      modifier = Modifier.fillMaxSize().weight(4f).padding(all = 4.dp)
        .border(width = 5.dp, color = Color.Red)
    ) {
      for (i in 0..14) {
        item {
          TextField(
            onValueChange = {
              val newStringsList = state.listWithVarsInitInfo.toMutableList()
              newStringsList[i] = it
              state.listWithVarsInitInfo = newStringsList
            },
            value = state.listWithVarsInitInfo[i],
            textStyle = TextStyle(fontSize = 15.sp),
            singleLine = true,
            modifier = Modifier.fillMaxSize().weight(1f)
          )
        }
      }
    }
    Surface(
      shape = RoundedCornerShape(15.dp),
      border = BorderStroke(2.dp, Color.Magenta),
      elevation = 10.dp,
      modifier = Modifier.fillMaxSize().weight(1f).padding(all = 4.dp)
    ) {
      Text(
        modifier = Modifier.padding(start = 2.dp, top = 4.dp),
        text = state.resultStr,
        style = MaterialTheme.typography.h1
      )
    }
    Surface(
      shape = RoundedCornerShape(15.dp),
      border = BorderStroke(2.dp, Color.Yellow),
      elevation = 10.dp,
      modifier = Modifier.fillMaxSize().weight(1f).padding(all = 4.dp)
    ) {
      Text(
        modifier = Modifier.padding(start = 2.dp, top = 4.dp),
        text = state.depthStr,
        style = MaterialTheme.typography.h1
      )
    }
    Surface(
      shape = RoundedCornerShape(15.dp),
      border = BorderStroke(2.dp, Color.Cyan),
      elevation = 10.dp,
      modifier = Modifier.fillMaxSize().weight(3f).padding(all = 4.dp)
    ) {
      Text(
        modifier = Modifier.padding(all = 7.dp),
        text = state.debugRepresentationStr,
        style = MaterialTheme.typography.h2
      )
    }
  }
}

@Composable
@Preview
fun processCalcGUI(state: AppState) {
  MaterialTheme(
    typography =
    Typography(
      button = TextStyle(fontSize = 30.sp),
      h1 = TextStyle(fontSize = 23.sp, letterSpacing = 2.sp),
      h2 = TextStyle(fontSize = 15.sp, textAlign = TextAlign.Center)
    )
  ) {
    Row(modifier = Modifier.fillMaxSize()) {
      processCalcButtons(state)
      processRightPartOfInterface(state)
    }
  }
}
