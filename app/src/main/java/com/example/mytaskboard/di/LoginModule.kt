package com.example.mytaskboard.di

import com.example.mytaskboard.login.LoginLiveDataWrapper
import com.example.mytaskboard.login.LoginResultMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginModule {

    @Binds
    @ViewModelScoped
    abstract fun liveDataWrapper(impl: LoginLiveDataWrapper.Base): LoginLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun loginResultMapper(impl: LoginResultMapper.Base): LoginResultMapper
}