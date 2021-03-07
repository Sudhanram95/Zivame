package com.example.zivame_assignment.ui.gadgetlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.zivame_assignment.BaseTest
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.database.ZivameDatabase
import com.example.zivame_assignment.network.NetworkState
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListApiService
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListRepository
import com.example.zivame_assignment.ui.gadgetlist.viewmodel.GadgetListViewModel
import com.google.common.util.concurrent.MoreExecutors
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class GadgetListViewModelTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: GadgetListViewModel
    @Mock lateinit var repository: GadgetListRepository
    @Mock lateinit var apiService: GadgetListApiService
    @Mock lateinit var response: GadgetListResponse
    @Mock
    lateinit var observerResponse: Observer<NetworkState<List<ProductModel>>>

    @Mock lateinit var cartDao: CartDao
    @Mock lateinit var cartEntity: CartEntity
    @Mock lateinit var observerAddItem: Observer<String>

    @Before
    fun setUp() {
        val executorService = MoreExecutors.newDirectExecutorService()
        repository = GadgetListRepository(apiService, executorService, cartDao)
        viewModel = GadgetListViewModel(repository)
        viewModel.getGadgetList().observeForever(observerResponse)
        viewModel.getToastMessage().observeForever(observerAddItem)
    }

    @Test
    fun testGetRepository() {
        assertEquals(repository, viewModel.repository)
    }

    @Test
    fun testFetchAllGadgets() {
        Mockito.`when`(apiService.getAllGadgetsList()).thenReturn(Single.just(response))
        latch.await(500, TimeUnit.MILLISECONDS)
        viewModel.fetchAllGadgets()
        val networkState = viewModel.getGadgetList().value
        assertTrue(networkState is NetworkState.Success)
    }

    @Test
    fun testFetchAllGadgetsError() {
        val exception = Exception()
        Mockito.`when`(apiService.getAllGadgetsList()).thenReturn(Single.error(exception))
        latch.await(500, TimeUnit.MILLISECONDS)
        viewModel.fetchAllGadgets()
        val networkState = viewModel.getGadgetList().value
        assertTrue(networkState is NetworkState.Error)
    }

    @Test
    fun testCreateCartEntity() {
        val testCartEntity = viewModel.createCartEntity(1, ProductModel())
        assertEquals(1, testCartEntity.itemId)
        assertEquals("", testCartEntity.price)
        assertEquals("", testCartEntity.itemName)
        assertEquals("", testCartEntity.imageUrl)
    }

    @Test
    fun testAddGadgetToCart() {
        `when`(cartDao.addItemToCart(cartEntity)).thenReturn(2)
        viewModel.addGadgetToCart(cartEntity)
        assertEquals("Added to cart successfully", viewModel.getToastMessage().value)
    }

    @Test
    fun testAddGadgetToCartFailure() {
        `when`(cartDao.addItemToCart(cartEntity)).thenReturn(-1)
        viewModel.addGadgetToCart(cartEntity)
        assertEquals("Already added to cart", viewModel.getToastMessage().value)
    }
}