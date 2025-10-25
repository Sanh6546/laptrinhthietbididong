package com.example.uicomponents.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Text Detail") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(), // chiếm toàn màn hình
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                buildAnnotatedString {
                    append("The ")

                    withStyle(
                        style = SpanStyle(textDecoration = TextDecoration.LineThrough)
                    ) {
                        append("quick ")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFB87333), // màu nâu đồng
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp // chữ “Brown” to hơn rõ
                        )
                    ) {
                        append("Brown\n")
                    }

                    append("fox j u m p s ")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over\n")
                    }

                    append("the ")

                    withStyle(
                        style = SpanStyle(
                            fontStyle = FontStyle.Italic,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("lazy ")
                    }

                    append("dog.")
                },
                fontSize = 24.sp, // toàn bộ chữ lớn hơn
                lineHeight = 36.sp, // giãn dòng rộng hơn
                modifier = Modifier
                    .padding(horizontal = 32.dp) // tăng độ rộng vùng text
            )
        }
    }
}
