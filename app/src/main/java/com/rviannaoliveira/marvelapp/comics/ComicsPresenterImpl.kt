package com.rviannaoliveira.marvelapp.comics

import com.rviannaoliveira.marvelapp.data.DataManager
import com.rviannaoliveira.marvelapp.model.Favorite
import timber.log.Timber

/**
 * Criado por rodrigo on 14/04/17.
 */
class ComicsPresenterImpl(private val view: ComicsView) : ComicsPresenter {

    override fun getMarvelComics(offset: Int) {
        if (offset == 0) {
            view.showProgressBar()
        }
        val observableComics = DataManager.getMarvelComics(offset)
        observableComics.subscribe({ marvelComics ->
            view.loadComics(marvelComics)
            view.hideProgressBar()
        }, { error ->
            view.hideProgressBar()
            view.error()
            Timber.w(error.message)
        })
    }

    override fun getMarvelComicsBeginLetter(letter: String) {
        view.showProgressBar()
        val observableComics = DataManager.getMarvelComicsBeginLetter(letter)
        observableComics.subscribe({ marvelComics ->
            view.loadFilterComics(marvelComics)
            view.hideProgressBar()
        }, { error ->
            view.hideProgressBar()
            view.error()
            Timber.w(error.message)
        })
    }

    override fun toggleFavorite(favorite: Favorite, checked: Boolean) {
        if (checked) {
            DataManager.insertFavorite(favorite)
        } else {
            DataManager.deleteFavorite(favorite)
        }
    }


}