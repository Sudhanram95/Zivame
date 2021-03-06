package com.example.zivame_assignment.database

interface DatabaseCallback {
    fun onSuccess(response: Any)
    fun onFailure()
}