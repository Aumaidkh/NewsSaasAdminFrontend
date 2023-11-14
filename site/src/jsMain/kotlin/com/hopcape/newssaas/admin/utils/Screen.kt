package com.hopcape.newssaas.admin.utils

sealed class Screen(val route: String){
    data object Create: Screen("articles/create")
    data object Home: Screen("/")
}