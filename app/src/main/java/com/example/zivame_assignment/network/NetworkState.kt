package com.example.zivame_assignment.network

sealed class NetworkState<T> {
    class Loading<T>(): NetworkState<T>()
    class Success<T>(val data: T): NetworkState<T>()
    class Error<T>(val error: Throwable): NetworkState<T>()
}