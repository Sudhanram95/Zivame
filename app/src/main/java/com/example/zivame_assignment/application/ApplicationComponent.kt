package com.example.zivame_assignment.application

import android.app.Application
import com.example.zivame_assignment.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component(modules = [NetworkModule::class,
                        ActivityBuilderModule::class])
interface ApplicationComponent : AndroidInjector<ZivameApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}