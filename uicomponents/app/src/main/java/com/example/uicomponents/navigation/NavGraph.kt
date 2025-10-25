package com.example.uicomponents.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uicomponents.screens.*

sealed class Screen(val route: String) {
    object Intro : Screen("intro")
    object UIList : Screen("ui_list")
    object TextDetail : Screen("text_detail")
    object ImageDetail : Screen("image_detail")
    object TextFieldDetail : Screen("textfield_detail")
    object PasswordFieldDetail : Screen("password_field_detail") // 👈 thêm dòng này
    object ColumnDetail : Screen("column_detail")

    object RowDetail : Screen("row_detail")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Intro.route) {
        composable(Screen.Intro.route) { IntroScreen(navController) }
        composable(Screen.UIList.route) { UIListScreen(navController) }
        composable(Screen.TextDetail.route) { TextScreen() }
        composable(Screen.ImageDetail.route) { ImageScreen() }
        composable(Screen.TextFieldDetail.route) { TextFieldScreen() }
        composable(Screen.ColumnDetail.route) { ColumnDetailScreen() }

        composable(Screen.RowDetail.route) { RowScreen() }
        composable(Screen.PasswordFieldDetail.route) {
            PasswordFieldDetailScreen(navController)
        }

    }
}
