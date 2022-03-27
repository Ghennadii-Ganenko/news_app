package main.app.newspaper.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import main.app.newspaper.domain.models.ArticleEntity
import main.app.newspaper.network.repository.ArticlesRepository
import timber.log.Timber


class ArticlesViewModel(private val articlesRepository: ArticlesRepository) : ViewModel() {
    val articlesList: LiveData<List<ArticleEntity>> = articlesRepository.articlesList
    var position: Int = 0

    override fun onCleared() {
        super.onCleared()
        Timber.d("VM cleared")
    }


    fun getAllArticlesList() {
        viewModelScope.launch(Dispatchers.IO) {
            articlesRepository.getAllArticlesList()
        }
    }
}