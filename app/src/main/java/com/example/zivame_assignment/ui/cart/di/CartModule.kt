package com.example.zivame_assignment.ui.cart.di

import com.example.zivame_assignment.application.ZivameApplication
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
    fun provideCartRepository(dbInstance: ZivameDatabase?): CartRepository {
        return CartRepository(dbInstance)
    }
}