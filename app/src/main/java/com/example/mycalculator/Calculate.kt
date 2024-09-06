package com.example.mycalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "AC", "0", ".", "="
)

@Composable
fun Calculate(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()


    Box(modifier = Modifier) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.End) {
            Text(modifier = Modifier.padding(top = 40.dp, end = 30.dp),
                text = equationText.value?:"Enter Data", style = TextStyle(
                    fontSize = 30.sp, textAlign = TextAlign.End, fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = resultText.value?:"", style = TextStyle(
                    fontSize = 50.sp, textAlign = TextAlign.End,
                    fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp).padding(bottom = 8.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(buttonList) {
                    CalculatorButton(btn = it, onClick = {
                        viewModel.onButtonClick(it)
                    })
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(10.dp)) {
        FloatingActionButton(
            onClick =  onClick ,
            modifier = Modifier.size(70.dp),
            shape = RoundedCornerShape(30.dp),
            containerColor = getColor(btn),
            contentColor = Color.Black

        ) {
            Text(
                text = btn, fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// For Making Dynamic Color of Different Buttons


fun getColor(btn: String): Color {
    return when (btn) {
        "C", "AC" -> Color(0xFF58AC84) // Red
        "(", ")" -> Color(0xFFAEDD8C)
        "/", "*", "-", "+" -> Color(0xFFCE96DD)
        "0", "." -> Color(0xFFECC449)
        "=" -> Color(0xFF7BDBDB)
        else -> Color(0xFFECC449)
    }
}
