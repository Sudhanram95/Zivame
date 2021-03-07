package com.example.zivame_assignment.ui.cart.di

import com.example.zivame_assignment.application.ZivameApplication
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.ZivameDatabase
import com.example.zivame_assignment.ui.cart.repository.CartRepository
import dagger.Module
import dagger.Provides

@Module
class CartModule {

    @CartScope
    @Provides
    fun provideDbInstance(): ZivameDatabase? {
        return ZivameDatabase.getDatabase(ZivameApplication.getApplicationContext())
    }

    @CartScope
    @Provides
    fun provideCartDao(dbInstance: ZivameDatabase?): CartDao? {
        return dbInstance?.cartDao()
    }

    @CartScope
    @Provides
    fun provideCartRepository(cartDao: CartDao?): CartRepository {
        return CartRepository(cartDao)
    }
}