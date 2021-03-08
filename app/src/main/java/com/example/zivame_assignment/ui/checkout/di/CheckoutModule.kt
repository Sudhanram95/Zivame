package com.example.zivame_assignment.ui.checkout.di

import com.example.zivame_assignment.application.ZivameApplication
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.ZivameDatabase
import com.example.zivame_assignment.ui.checkout.repository.CheckoutRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService

@Module
class CheckoutModule {

    @CheckoutScope
    @Provides
    fun provideCheckoutRepository(executorService: ExecutorService, cartDao: CartDao?): CheckoutRepository {
        return CheckoutRepository(executorService, cartDao)
    }
}