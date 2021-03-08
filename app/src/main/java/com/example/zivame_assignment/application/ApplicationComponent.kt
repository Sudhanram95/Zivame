package com.example.zivame_assignment.application

import android.app.Application
import com.example.zivame_assignment.application.viewmodel.ViewModelFactoryModule
import com.example.zivame_assignment.database.DatabaseModule
import com.example.zivame_assignment.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                        NetworkModule::class,
                        DatabaseModule::class,
                        ActivityBuilderModule::class,
                        ViewModelFactoryModule::class])
interface ApplicationComponent : AndroidInjector<ZivameApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}