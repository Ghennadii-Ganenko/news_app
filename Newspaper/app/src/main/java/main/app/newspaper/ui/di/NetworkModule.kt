package main.app.newspaper.ui.di

import main.app.newspaper.network.repository.ArticlesRepository
import org.koin.dsl.module

val networkModule = module {
    single {
        ArticlesRepository()
    }
}