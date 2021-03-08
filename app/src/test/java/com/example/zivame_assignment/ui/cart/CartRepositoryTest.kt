package com.example.zivame_assignment.ui.cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.zivame_assignment.BaseTest
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.ui.cart.repository.CartRepository
import com.google.common.util.concurrent.MoreExecutors
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class CartRepositoryTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var cartDao: CartDao
    @Mock lateinit var databaseCallback: DatabaseCallback

    lateinit var repository: CartRepository

    @Before
    fun setUp() {
        val executorService = MoreExecutors.newDirectExecutorService()
        repository = CartRepository(executorService, cartDao)
    }

    @Test
    fun testGetAllItemsInCartTable() {
        val testEntity = CartEntity()
        val testCartList = ArrayList<CartEntity>()
        testCartList.add(testEntity)

        `when`(cartDao.getAllItemsInCart()).thenReturn(testCartList)
        repository.getAllItemsInCartTable(databaseCallback)

        val inOrder = inOrder(databaseCallback)
        inOrder.verify(databaseCallback, times(1)).onSuccess(testCartList)
        verify(databaseCallback, never()).onFailure()
    }

    @Test
    fun testGetAllItemsInCartTableFailure() {
        val testCartDao: CartDao? = null
        val executorService = MoreExecutors.newDirectExecutorService()
        val testRepository = CartRepository(executorService, testCartDao)

        testRepository.getAllItemsInCartTable(databaseCallback)

        val inOrder = inOrder(databaseCallback)
        inOrder.verify(databaseCallback, times(1)).onFailure()
        verify(databaseCallback, never()).onSuccess(any())
    }

    @Test
    fun testRemoveItemFromCartTable() {
        repository.removeItemFromCartTable(1, databaseCallback)

        val inOrder = inOrder(databaseCallback)
        inOrder.verify(databaseCallback, times(1)).onSuccess(anyString())
        verify(databaseCallback, never()).onFailure()
    }

    @Test
    fun testRemoveItemFromCartTableFailure() {
        val testCartDao: CartDao? = null
        val executorService = MoreExecutors.newDirectExecutorService()
        val testRepository = CartRepository(executorService, testCartDao)

        testRepository.removeItemFromCartTable(1, databaseCallback)

        val inOrder = inOrder(databaseCallback)
        inOrder.verify(databaseCallback, times(1)).onFailure()
        verify(databaseCallback, never()).onSuccess(any())
    }

    @Test
    fun testGetTotalAmount() {
        val testTotalAmountLiveData = MutableLiveData<Int>()
        testTotalAmountLiveData.value = 100

        `when`(cartDao.getTotalAmount()).thenReturn(testTotalAmountLiveData)

        repository.getTotalAmount()

        assertNotNull(repository.getTotalAmount())
        assertEquals(100, repository.getTotalAmount()?.value)
    }
}