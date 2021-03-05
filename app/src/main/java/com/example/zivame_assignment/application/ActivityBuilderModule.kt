package com.example.zivame_assignment.application

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
}