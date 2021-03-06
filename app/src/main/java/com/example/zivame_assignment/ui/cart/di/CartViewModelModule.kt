package com.example.zivame_assignment.ui.cart.di

import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.application.viewmodel.ViewModelKey
import com.example.zivame_assignment.ui.cart.viewmodel.CartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CartViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    protected abstract fun bindCartViewModel(cartViewModel: CartViewModel): ViewModel
}