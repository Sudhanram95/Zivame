package com.example.zivame_assignment.ui.cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.zivame_assignment.BaseTest
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.ui.cart.repository.CartRepository
import com.example.zivame_assignment.ui.cart.viewmodel.CartViewModel
import com.google.common.util.concurrent.MoreExecutors
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class CartViewModelTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: CartViewModel
    lateinit var repository: CartRepository

    @Mock lateinit var cartDao: CartDao
    @Mock lateinit var observerCartList: Observer<List<CartEntity>>
    @Mock lateinit var observerResult: Observer<String>
    @Mock lateinit var observerShowEmptyCart: Observer<Boolean>

    @Before
    fun setUp() {
        val executorService = MoreExecutors.newDirectExecutorService()
        repository = CartRepository(executorService, cartDao)
        viewModel = CartViewModel(repository)

        viewModel.getCartList().observeForever(observerCartList)
        viewModel.getResult().observeForever(observerResult)
        viewModel.getShowEmptyCart().observeForever(observerShowEmptyCart)
    }

    @Test
    fun testFetchAllItemsInCartCase1() {
        val testEntity = CartEntity()
        val testCartList = ArrayList<CartEntity>()
        testCartList.add(testEntity)

        `when`(cartDao.getAllItemsInCart()).thenReturn(testCartList)
        viewModel.fetchAllItemsInCart()
        assertNull(viewModel.getShowEmptyCart().value)
        assertNotNull(viewModel.getCartList().value)
        assertEquals(1, viewModel.getCartList().value?.size)
    }

    @Test
    fun testFetchAllItemsInCartCase2() {
        val testCartList = ArrayList<CartEntity>()

        `when`(cartDao.getAllItemsInCart()).thenReturn(testCartList)
        viewModel.fetchAllItemsInCart()
        assertNotNull(viewModel.getShowEmptyCart().value)
        assertTrue(viewModel.getShowEmptyCart().value!!)
        assertNotNull(viewModel.getCartList().value)
        assertEquals(0, viewModel.getCartList().value?.size)
    }

    @Test
    fun testFetchAllItemsInCartFailure() {
        val testCartDao: CartDao? = null
        val executorService = MoreExecutors.newDirectExecutorService()
        val testRepository = CartRepository(executorService, testCartDao)
        val testViewModel = CartViewModel(testRepository)
        testViewModel.fetchAllItemsInCart()
        assertNull(testViewModel.getCartList().value?.size)
        assertEquals("Could not get Items!", testViewModel.getResult().value)
    }

    @Test
    fun testRemoveItemFromCart() {
        viewModel.removeItemFromCart(1)
        assertNotNull(viewModel.getResult().value)
        assertEquals("Item removed successfully", viewModel.getResult().value)
    }

    @Test
    fun testRemoveItemFromCartFailure() {
        val testCartDao: CartDao? = null
        val executorService = MoreExecutors.newDirectExecutorService()
        val testRepository = CartRepository(executorService, testCartDao)
        val testViewModel = CartViewModel(testRepository)

        testViewModel.removeItemFromCart(1)
        assertEquals("Could not remove item!", testViewModel.getResult().value)
    }

    @Test
    fun testCheckIfCartIsEmptyCase1() {
        viewModel.checkIfCartIsEmpty(null)
        assertNotNull(viewModel.getShowEmptyCart().value)
        assertTrue(viewModel.getShowEmptyCart().value!!)
    }

    @Test
    fun testCheckIfCartIsEmptyCase2() {
        viewModel.checkIfCartIsEmpty(100)
        assertNull(viewModel.getShowEmptyCart().value)
    }
}