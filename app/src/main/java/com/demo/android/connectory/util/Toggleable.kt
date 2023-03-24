package com.demo.android.connectory.util

data class Toggleable<T>(
    val item: T,
    var isSelected: Boolean = false,
)
