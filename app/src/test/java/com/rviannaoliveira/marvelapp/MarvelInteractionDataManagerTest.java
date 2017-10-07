package com.rviannaoliveira.marvelapp;

import com.rviannaoliveira.marvelapp.data.DataManager;
import com.rviannaoliveira.marvelapp.data.api.ApiData;
import com.rviannaoliveira.marvelapp.data.repository.RepositoryData;
import com.rviannaoliveira.marvelapp.model.Favorite;
import com.rviannaoliveira.marvelapp.model.MarvelCharacter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.robolectric.annotation.Config;

import java.util.Collections;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;
import io.reactivex.Flowable;

import static org.mockito.Mockito.when;

/**
 * Criado por rodrigo on 24/09/17.
 */
@RunWith(MockitoJUnitRunner.class)
@Config(constants = BuildConfig.class)
public class MarvelInteractionDataManagerTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    public ApiData apiData;

    @Mock
    public RepositoryData repositoryData;

    @Test
    public void loadMarvelCharacters() {
        DataManager dataManager = getDataManager();
        List<MarvelCharacter> characters = Arrays.asList(new MarvelCharacter[]{new MarvelCharacter(1)});

        when(apiData.getMarvelCharacters(0)).thenReturn(Flowable.just(characters));
        when(repositoryData.getCharactersFavorites()).thenReturn(Flowable.just(Collections.<Favorite>emptyList()));
        dataManager.getMarvelCharacters(0).test().assertValue(characters);
    }


    private DataManager getDataManager() {
        return new DataManager(apiData, repositoryData);
    }
}