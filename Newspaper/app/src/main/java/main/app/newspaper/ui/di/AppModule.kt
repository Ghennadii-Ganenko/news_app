package main.app.newspaper.ui.di

import main.app.newspaper.ui.fragment.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<ArticlesViewModel> {
        ArticlesViewModel(
            articlesRepository = get()
        )
    }
}