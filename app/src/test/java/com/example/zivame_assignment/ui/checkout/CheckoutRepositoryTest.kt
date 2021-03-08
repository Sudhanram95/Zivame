package com.example.zivame_assignment.ui.checkout

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zivame_assignment.BaseTest
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.ui.checkout.repository.CheckoutRepository
import com.google.common.util.concurrent.MoreExecutors
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class CheckoutRepositoryTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var cartDao: CartDao
    @Mock lateinit var databaseCallback: DatabaseCallback

    lateinit var repository: CheckoutRepository

    @Before
    fun setUp() {
        val executorService = MoreExecutors.newDirectExecutorService()
        repository = CheckoutRepository(executorService, cartDao)
    }

    @Test
    fun testDeleteAllItemsFromCart() {
        repository.deleteAllItemsFromCart(databaseCallback)

        val inOrder = Mockito.inOrder(databaseCallback)
        inOrder.verify(databaseCallback, Mockito.times(1)).onSuccess(Mockito.anyString())
        Mockito.verify(databaseCallback, Mockito.never()).onFailure()
    }

    @Test
    fun testDeleteAllItemsFromCartFailure() {
        val testCartDao: CartDao? = null
        val executorService = MoreExecutors.newDirectExecutorService()
        val testRepository = CheckoutRepository(executorService, testCartDao)

        testRepository.deleteAllItemsFromCart(databaseCallback)

        val inOrder = Mockito.inOrder(databaseCallback)
        inOrder.verify(databaseCallback, Mockito.times(1)).onFailure()
        Mockito.verify(databaseCallback, Mockito.never()).onSuccess(any())
    }
}