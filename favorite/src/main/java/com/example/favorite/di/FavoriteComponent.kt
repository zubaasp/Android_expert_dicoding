package com.example.favorite.di

import android.content.Context
import com.example.core.di.FavoriteModule
import com.example.favorite.ui.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModule::class])
interface FavoriteComponent {
    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModule): Builder
        fun build(): FavoriteComponent
    }
}