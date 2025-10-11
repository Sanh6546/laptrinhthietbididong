package com.example.checkemail // ĐÃ CẬP NHẬT TÊN PACKAGE

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Cần đảm bảo đường dẫn import theme này là đúng trong project của bạn
import com.example.checkemail.ui.theme.CheckemailTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // SỬ DỤNG THEME CỦA PROJECT HIỆN TẠI
            CheckemailTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckEmail()
                }
            }
        }
    }
}

// ------------------------------------------------------------------

@Composable
fun CheckEmail() {
    // State lưu trữ giá trị Email người dùng nhập
    var emailText by remember { mutableStateOf("") }

    // State lưu trữ thông báo kết quả/lỗi cuối cùng
    var validationMessage by remember { mutableStateOf("") }

    // State để bật/tắt trạng thái lỗi trực quan (isError) cho TextField
    var isEmailError by remember { mutableStateOf(false) }

    // Logic xử lý khi nhấn nút "Kiểm tra"
    val onCheckClick: () -> Unit = {
        val email = emailText.trim()
        isEmailError = false
        validationMessage = ""

        when {
            // 1. Kiểm tra Email null hoặc rỗng
            email.isEmpty() -> {
                validationMessage = "Email không hợp lệ"
                isEmailError = true
            }
            // 2. Kiểm tra Email không chứa ký tự '@'
            !email.contains("@") -> {
                validationMessage = "Email không đúng định dạng"
                isEmailError = true
            }
            // 3. Hợp lệ
            else -> {
                validationMessage = "Bạn đã nhập email hợp lệ"
                isEmailError = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Tiêu đề
        Text(
            text = "Thực hành 02",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Khu vực nhập liệu và nút (Đã căn giữa)
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 24.dp)
        ) {
            OutlinedTextField(
                value = emailText,
                onValueChange = { newValue ->
                    emailText = newValue
                    // Reset trạng thái khi người dùng bắt đầu nhập
                    validationMessage = ""
                    isEmailError = false
                },
                label = { Text("Email") },
                isError = isEmailError,

                // Cố định chiều cao cho supporting text để tránh lỗi con trỏ nhảy
                supportingText = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (isEmailError) {
                            Text(
                                text = validationMessage,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 12.sp,
                                modifier = Modifier.fillMaxWidth()
                            )
                        } else {
                            // Giữ chỗ bằng Spacer với chiều cao cố định
                            Spacer(modifier = Modifier.height(18.dp))
                        }
                    }
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Nút "Kiểm tra"
            Button(
                onClick = onCheckClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    // Đổi màu nút dựa trên trạng thái hợp lệ
                    containerColor = if (validationMessage == "Bạn đã nhập email hợp lệ")
                        Color(0xFF1E88E5) // Xanh đậm khi thành công
                    else Color(0xFF64B5F6) // Xanh nhạt
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Kiểm tra")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Hiển thị thông báo thành công
            if (validationMessage.isNotEmpty() && !isEmailError) {
                Text(
                    text = validationMessage,
                    color = Color(0xFF4CAF50), // Màu xanh lá cây
                    fontSize = 16.sp
                )
            }
        }
    }
}

// ------------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun PreviewCheckEmail() {
    CheckemailTheme { // SỬ DỤNG THEME CỦA PROJECT HIỆN TẠI
        CheckEmail()
    }
}