package com.example.zivame_assignment.network

interface NetworkCallback {
    fun onSuccess(response: Any)
    fun onError(error: Throwable)
}