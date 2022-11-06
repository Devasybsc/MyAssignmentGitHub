package com.example.myassignmentgithub.databinding

import androidx.annotation.LayoutRes

interface BindableItemViewModel {
    @get:LayoutRes
    val layoutId: Int
    val viewType: Int
        get() = 0
}