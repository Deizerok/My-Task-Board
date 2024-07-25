package com.example.mytaskboard.di

import com.example.mytaskboard.main.Navigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    abstract fun bindsNavigationNavigate(navigation: Navigation.Base): Navigation.Navigate

    @Binds
    abstract fun bindsNavigationMutable(navigation: Navigation.Base): Navigation.Mutable

}