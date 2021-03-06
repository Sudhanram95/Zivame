package com.example.zivame_assignment.network

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NetworkStateTest {

    lateinit var successState: NetworkState.Success<String>
    lateinit var errorState: NetworkState.Error<String>

    @Before
    fun setUp() {
        successState = NetworkState.Success("test success")
        errorState = NetworkState.Error(Throwable("test error"))
    }

    @Test
    fun testSuccess() {
        Assert.assertEquals("test success", successState.data)
    }

    @Test
    fun testError() {
        Assert.assertEquals("test error", errorState.error?.message)
    }
}