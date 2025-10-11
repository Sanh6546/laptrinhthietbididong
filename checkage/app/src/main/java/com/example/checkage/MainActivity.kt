package com.example.checkage // Đảm bảo khớp với package: com.example.checkage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Đảm bảo import theme này khớp với project của bạn
import com.example.checkage.ui.theme.CheckageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckageTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckAgeGroup()
                }
            }
        }
    }
}

// ------------------------------------------------------------------

@Composable
fun CheckAgeGroup() {
    // State cho Họ và tên
    var nameText by remember { mutableStateOf("") }
    // State cho Tuổi
    var ageText by remember { mutableStateOf("") }

    // State cho kết quả cuối cùng (tên và nhóm tuổi)
    var resultMessage by remember { mutableStateOf<String?>(null) }

    // State để theo dõi lỗi nhập liệu
    var inputError by remember { mutableStateOf(false) }

    // Hàm xác định nhóm tuổi
    fun getAgeGroup(age: Int): String {
        return when {
            // Người già (>65)
            age > 65 -> "Người già (>65)"
            // Người lớn (7-65) - Điều chỉnh để tránh chồng chéo
            age >= 7 && age <= 65 -> "Người lớn (6-65)"
            // Trẻ em (2-6)
            age >= 2 && age <= 6 -> "Trẻ em (2-6)"
            // Em bé (<2)
            age < 2 -> "Em bé (<2)"
            else -> "Không xác định"
        }
    }

    // Logic xử lý khi nhấn nút "Kiểm tra"
    val onCheckClick: () -> Unit = {
        resultMessage = null
        inputError = false
        val trimmedName = nameText.trim()
        val age = ageText.trim().toIntOrNull()

        if (trimmedName.isEmpty()) {
            resultMessage = "Vui lòng nhập Họ và tên."
            inputError = true
        } else if (age == null || age < 0) {
            resultMessage = "Vui lòng nhập Tuổi hợp lệ (là số nguyên dương)."
            inputError = true
        } else {
            // Xác định nhóm tuổi và hiển thị kết quả
            val group = getAgeGroup(age)
            resultMessage = "Xin chào $trimmedName, bạn thuộc nhóm: $group"
            inputError = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Tiêu đề
        Text(
            text = "THỰC HÀNH 01",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 28.sp),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Khối nhập liệu (nền xám nhẹ)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE), RoundedCornerShape(8.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Trường nhập Họ và tên
            InputRow(
                label = "Họ và tên",
                value = nameText,
                onValueChange = { nameText = it },
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Trường nhập Tuổi
            InputRow(
                label = "Tuổi",
                value = ageText,
                onValueChange = {
                    // Chỉ cho phép nhập số
                    if (it.all { char -> char.isDigit() } || it.isEmpty()) {
                        ageText = it
                    }
                },
                keyboardType = KeyboardType.Number
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Nút "Kiểm tra"
        Button(
            onClick = onCheckClick,
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Kiểm tra")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Hiển thị kết quả
        if (resultMessage != null) {
            Text(
                text = resultMessage!!,
                color = if (inputError) MaterialTheme.colorScheme.error else Color(0xFF4CAF50),
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

// Composable dùng chung cho hàng nhập liệu
@Composable
fun InputRow(label: String, value: String, onValueChange: (String) -> Unit, keyboardType: KeyboardType) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Label
        Text(
            text = label,
            modifier = Modifier.width(100.dp),
            fontSize = 16.sp
        )

        // TextField
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            label = { Text("") }, // Label trống vì đã có Text ngoài
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            // ĐÃ XÓA CHIỀU CAO CỐ ĐỊNH (height) để khắc phục lỗi bị che
            modifier = Modifier.weight(1f)
        )
    }
}

// ------------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun PreviewCheckAgeGroup() {
    CheckageTheme {
        CheckAgeGroup()
    }
}