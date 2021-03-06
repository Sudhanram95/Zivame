package com.example.zivame_assignment.application

import com.example.zivame_assignment.ui.cart.di.CartModule
import com.example.zivame_assignment.ui.cart.di.CartScope
import com.example.zivame_assignment.ui.cart.di.CartViewModelModule
import com.example.zivame_assignment.ui.cart.view.CartActivity
import com.example.zivame_assignment.ui.gadgetlist.di.GadgetListModule
import com.example.zivame_assignment.ui.gadgetlist.di.GadgetListScope
import com.example.zivame_assignment.ui.gadgetlist.di.GadgetListViewModelModule
import com.example.zivame_assignment.ui.gadgetlist.view.GadgetListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @GadgetListScope
    @ContributesAndroidInjector(modules = [GadgetListModule::class, GadgetListViewModelModule::class])
    abstract fun contributeGadgetListActivity(): GadgetListActivity

    @CartScope
    @ContributesAndroidInjector(modules = [CartModule::class, CartViewModelModule::class])
    abstract fun contributeCartActivity(): CartActivity
}