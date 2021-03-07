package com.example.zivame_assignment.ui.checkout.di

import com.example.zivame_assignment.application.ZivameApplication
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.ZivameDatabase
import com.example.zivame_assignment.ui.checkout.repository.CheckoutRepository
import dagger.Module
import dagger.Provides

@Module
class CheckoutModule {

    @CheckoutScope
    @Provides
    fun provideDbInstance(): ZivameDatabase? {
        return ZivameDatabase.getDatabase(ZivameApplication.getApplicationContext())
    }

    @CheckoutScope
    @Provides
    fun provideCartDao(dbInstance: ZivameDatabase?): CartDao? {
        return dbInstance?.cartDao()
    }

    @CheckoutScope
    @Provides
    fun provideCheckoutRepository(cartDao: CartDao?): CheckoutRepository {
        return CheckoutRepository(cartDao)
    }
}