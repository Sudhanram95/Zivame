package com.example.zivame_assignment.ui.checkout.di

import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.application.viewmodel.ViewModelKey
import com.example.zivame_assignment.ui.checkout.viewmodel.CheckoutViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CheckoutViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CheckoutViewModel::class)
    protected abstract fun bindCheckoutViewModel(checkoutViewModel: CheckoutViewModel): ViewModel
}