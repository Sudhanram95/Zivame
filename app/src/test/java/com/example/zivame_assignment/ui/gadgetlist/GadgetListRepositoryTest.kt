package com.example.zivame_assignment.ui.gadgetlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zivame_assignment.BaseTest
import com.example.zivame_assignment.network.NetworkCallback
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListApiService
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListRepository
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class GadgetListRepositoryTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var apiService: GadgetListApiService
    @Mock lateinit var response: GadgetListResponse
    @Mock lateinit var networkCallback: NetworkCallback
    @Mock lateinit var ex: Exception

    lateinit var repository: GadgetListRepository

    @Before
    fun setUp() {
        repository = GadgetListRepository(apiService)
    }

    @Test
    fun testGetApiService() {
        assertNotNull(repository.apiService)
        assertEquals(repository.apiService, apiService)
    }

    @Test
    fun testGetAllGadgetsFromApiSuccess() {
        Mockito.`when`(apiService.getAllGadgetsList()).thenReturn(Single.just(response))
        repository.getAllGadgetsFromApi(networkCallback)

        latch.await(500, TimeUnit.MILLISECONDS)

        val inOrder = Mockito.inOrder(networkCallback)
        inOrder.verify(networkCallback, Mockito.times(1)).onSuccess(response)
        Mockito.verify(networkCallback, Mockito.never()).onError(ex)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetAllGadgetsFromApiFailure() {
        Mockito.`when`(apiService.getAllGadgetsList()).thenReturn(Single.error(ex))
        repository.getAllGadgetsFromApi(networkCallback)

        latch.await(500, TimeUnit.MILLISECONDS)

        val inOrder = Mockito.inOrder(networkCallback)
        inOrder.verify(networkCallback, Mockito.times(1)).onError(ex)
        Mockito.verify(networkCallback, Mockito.never()).onSuccess(response)
    }
}