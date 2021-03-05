package com.example.zivame_assignment.ui.gadgetlist.di

import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.ui.gadgetlist.viewmodel.GadgetListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GadgetListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GadgetListViewModel::class)
    protected abstract fun bindGadgetListViewModel(gadgetListViewModel: GadgetListViewModel): ViewModel
}