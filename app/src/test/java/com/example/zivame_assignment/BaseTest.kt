package com.example.zivame_assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
abstract class BaseTest {

    var latch = CountDownLatch(1)

    init {
        latch.await(100, TimeUnit.MILLISECONDS)
    }

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun baseSetup(){
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setIoSchedulerHandler { schedulerCallable: Scheduler? -> Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
    }

    fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    fun <T> uninitialized(): T = null as T

}