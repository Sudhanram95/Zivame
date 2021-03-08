package com.example.zivame_assignment.ui.gadgetlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.zivame_assignment.BaseTest
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.database.ZivameDatabase
import com.example.zivame_assignment.network.NetworkCallback
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListApiService
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListRepository
import com.google.common.util.concurrent.MoreExecutors
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
import org.mockito.Mockito.`when`
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class GadgetListRepositoryTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var apiService: GadgetListApiService
    @Mock lateinit var cartDao: CartDao
    @Mock lateinit var cartEntity: CartEntity
    @Mock lateinit var databaseCallback: DatabaseCallback
    @Mock lateinit var response: GadgetListResponse
    @Mock lateinit var networkCallback: NetworkCallback
    @Mock lateinit var ex: Exception

    lateinit var repository: GadgetListRepository

    @Before
    fun setUp() {
        val executorService = MoreExecutors.newDirectExecutorService()
        repository = GadgetListRepository(apiService, executorService, cartDao)
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

    @Test
    fun testAddGadgetToCart() {
        `when`(cartDao.addItemToCart(cartEntity)).thenReturn(2.toLong())
        repository.addGadgetToCart(cartEntity, databaseCallback)

        val inOrder = Mockito.inOrder(databaseCallback)
        inOrder.verify(databaseCallback, Mockito.times(1)).onSuccess(2.toLong())
        Mockito.verify(databaseCallback, Mockito.never()).onFailure()
    }

    @Test
    fun testAddGadgetToCartFailure() {
        `when`(cartDao.addItemToCart(cartEntity)).thenReturn(-1.toLong())
        repository.addGadgetToCart(cartEntity, databaseCallback)

        val inOrder = Mockito.inOrder(databaseCallback)
        inOrder.verify(databaseCallback, Mockito.times(1)).onFailure()
        Mockito.verify(databaseCallback, Mockito.never()).onSuccess(any())
    }

    @Test
    fun testGetBadgeCount() {
        val badgeCountLiveData = MutableLiveData<Int>()
        badgeCountLiveData.value = 10
        `when`(cartDao.getItemCount()).thenReturn(badgeCountLiveData)
        assertEquals(badgeCountLiveData, repository.getBadgeCountFromDb())
        assertEquals(10, repository.getBadgeCountFromDb()?.value)
    }
}