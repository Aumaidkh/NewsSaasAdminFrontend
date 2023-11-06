package com.hopcape.newssaas.admin.style

sealed class Shapes(val cornerRadius: Int){
    data object Small: Shapes(4)
    data object Medium: Shapes(8)
    data object Large: Shapes(12)
}