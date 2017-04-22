package com.rviannaoliveira.marvelapp.comics

import android.util.Log
import com.rviannaoliveira.marvelapp.data.DataManager
import com.rviannaoliveira.marvelapp.model.Favorite
import io.reactivex.Observable

/**
 * Criado por rodrigo on 14/04/17.
 */
class ComicsPresenterImpl(private val view: ComicsView) : ComicsPresenter {

    override fun getMarvelComics() {
        view.showProgressBar()
        val observableComics = DataManager.getMarvelComics()
        observableComics.subscribe({ marvelComics ->
            view.loadComics(marvelComics)
            view.hideProgressBar()
        }, { error ->
            Log.e("error", error.message)
        })
    }

    override fun getFavorites(): Observable<List<Favorite>> {
        return DataManager.getAllFavorites()
    }
    override fun toggleFavorite(favorite: Favorite, checked: Boolean) {
        if (checked) {
            DataManager.insertFavorite(favorite)
        } else {
            DataManager.deleteFavorite(favorite)
        }
    }


}