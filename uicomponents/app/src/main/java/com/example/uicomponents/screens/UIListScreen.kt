package com.example.uicomponents.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uicomponents.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIListScreen(navController: NavController) {
    val sections = listOf(
        "Display" to listOf(
            "Text" to Screen.TextDetail.route,
            "Image" to Screen.ImageDetail.route
        ),
        "Input" to listOf(
            "TextField" to Screen.TextFieldDetail.route,
            "PasswordField" to Screen.PasswordFieldDetail.route
        ),
        "Layout" to listOf(
            "Column" to Screen.ColumnDetail.route,
            "Row" to Screen.RowDetail.route
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "UI Components List",
                        color = Color(0xFF2196F3), // màu xanh tiêu đề
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            sections.forEach { (sectionTitle, items) ->
                item {
                    Text(
                        text = sectionTitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(items) { (title, route) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFBBDEFB), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .padding(bottom = 8.dp)
                            .clickable { navController.navigate(route) }
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D47A1)
                        )
                        Text(
                            text = when (title) {
                                "Text" -> "Displays text"
                                "Image" -> "Displays an image"
                                "TextField" -> "Input field for text"
                                "PasswordField" -> "Input field for passwords"
                                "Column" -> "Arranges elements vertically"
                                "Row" -> "Arranges elements horizontally"
                                else -> ""
                            },
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
