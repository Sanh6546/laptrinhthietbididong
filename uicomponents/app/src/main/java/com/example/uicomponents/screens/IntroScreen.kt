package com.example.uicomponents.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.uicomponents.R
import com.example.uicomponents.navigation.Screen

@Composable
fun IntroScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🟢 Logo ở giữa
            Image(
                painter = painterResource(id = R.drawable.huhu),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(140.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 🔵 Tiêu đề
            Text(
                text = "Jetpack Compose",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🟣 Mô tả căn đều hai bên
            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                lineHeight = 20.sp
            )
        }

        // 🔵 Nút “I’m ready” bo tròn ở dưới cùng
        Button(
            onClick = { navController.navigate(Screen.UIList.route) },
            shape = RoundedCornerShape(50), // bo tròn như hình
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3), // xanh sáng
                contentColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("I’m ready", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
