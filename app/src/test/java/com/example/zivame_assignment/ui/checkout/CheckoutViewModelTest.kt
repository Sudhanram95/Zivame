package com.example.zivame_assignment.ui.checkout

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.zivame_assignment.BaseTest
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.ui.checkout.repository.CheckoutRepository
import com.example.zivame_assignment.ui.checkout.viewmodel.CheckoutViewModel
import com.google.common.util.concurrent.MoreExecutors
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class CheckoutViewModelTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var cartDao: CartDao
    @Mock lateinit var observerShowLoading: Observer<Boolean>
    @Mock lateinit var observerRemoveAllItems: Observer<Boolean>

    lateinit var viewModel: CheckoutViewModel
    lateinit var repository: CheckoutRepository

    @Before
    fun setUp() {
        val executorService = MoreExecutors.newDirectExecutorService()
        repository = CheckoutRepository(executorService, cartDao)
        viewModel = CheckoutViewModel(repository)

        viewModel.getShowLoading().observeForever(observerShowLoading)
        viewModel.getRemoveAllItems().observeForever(observerRemoveAllItems)
    }

    @Test
    fun testRemoveAllItemsFromCart() {
        viewModel.removeAllItemsFromCart()
        assertNotNull(viewModel.getShowLoading().value)
        assertFalse(viewModel.getShowLoading().value!!)
    }
}