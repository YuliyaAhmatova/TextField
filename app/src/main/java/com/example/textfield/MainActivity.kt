package com.example.textfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen()
        }
    }
}

@Composable
fun MyScreen() {
    val input = rememberSaveable { mutableStateOf("") }
    val arrayList = remember {
        mutableStateListOf<String>()
    }

    Column {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(350.dp)
                .background(color = Color.LightGray)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.DarkGray)
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                text = "Динамический список",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.padding(top = 4.dp)
            )
            LazyColumn {
                items(arrayList) { item ->
                    Text(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .background(color = Color.White, shape = CircleShape)
                            .clickable {
                                arrayList.remove(item)
                            },
                        text = item,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            textStyle = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 70.dp, end = 70.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    if (input.value.isNotBlank()) {
                        arrayList.add(input.value)
                        input.value = ""
                    }
                },
            textAlign = TextAlign.Center,
            text = "Добавить",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
