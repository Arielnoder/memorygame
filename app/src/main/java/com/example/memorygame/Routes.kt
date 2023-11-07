package com.example.memorygame

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {



    object LoginPage : Screen("LoginPage", resourceId = R.string.Login)
    object Register : Screen("Register", resourceId = R.string.Register)
    object Game : Screen("Game", resourceId = R.string.Game)

    object Home : Screen("Home", resourceId = R.string.Home)

    object LeaderBoard : Screen("LeaderBoard", resourceId = R.string.LeaderBoard)






}