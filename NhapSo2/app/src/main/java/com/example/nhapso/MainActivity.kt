package com.example.nhapso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nhapso.ui.theme.NhapSoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NhapSoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExerciseScreen()
                }
            }
        }
    }
}

@Composable
fun ExerciseScreen() {
    var inputNumberText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var dataList by remember { mutableStateOf(listOf<Int>()) }
    val isError = errorMessage != null

    val onGenerateClick: () -> Unit = {
        val number = inputNumberText.trim().toIntOrNull()

        if (number != null && number > 0) {
            errorMessage = null
            dataList = (1..number).toList()
        } else {
            dataList = emptyList()
            errorMessage = "Dữ liệu bạn nhập không hợp lệ"
        }
    }

    // Sử dụng Column căn giữa màn hình
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Thực hành 02",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Dùng Row chiếm toàn bộ chiều rộng
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputNumberText,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                        inputNumberText = newValue
                        if (isError) errorMessage = null
                    }
                },
                label = { Text("Nhập số lượng") },
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            text = errorMessage ?: "",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onGenerateClick,
                modifier = Modifier.height(56.dp)
            ) {
                Text("Tạo")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (dataList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(dataList) { item ->
                    ListItemComposable(item.toString())
                }
            }
        }
    }
}

@Composable
fun ListItemComposable(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEF5350))
    ) {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseScreen() {
    NhapSoTheme {
        ExerciseScreen()
    }
}
