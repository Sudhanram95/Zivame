package com.example.zivame_assignment.ui.gadgetlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.zivame_assignment.BaseTest
import com.example.zivame_assignment.network.NetworkState
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListApiService
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListRepository
import com.example.zivame_assignment.ui.gadgetlist.viewmodel.GadgetListViewModel
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
import java.lang.Exception
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

    @Before
    fun setUp() {
        repository = GadgetListRepository(apiService)
        viewModel = GadgetListViewModel(repository)
        viewModel.getGadgetList().observeForever(observerResponse)
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
}