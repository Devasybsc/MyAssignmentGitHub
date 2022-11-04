package com.example.myassignmentgithub.ui

sealed class ScreenState {
    object StateProgress : ScreenState()
    data class StateError(val throwable: Throwable) : ScreenState()
    object UsersLoaded : ScreenState()
}