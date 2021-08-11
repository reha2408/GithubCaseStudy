package com.reha.casestudy.di.base;

import com.reha.casestudy.App;
import com.reha.casestudy.di.network.DataModule;
import com.reha.casestudy.feature.github.domain.GitHubRepository;
import com.reha.casestudy.feature.github.data.repo.datasource.GithubRemoteDataSource;
import com.reha.casestudy.feature.github.domain.interactor.GithubApiSearch;
import com.reha.casestudy.di.feature.GithubModule;
import com.reha.casestudy.di.viewmodel.ViewModelModule;
import com.reha.casestudy.feature.github.presentation.repodetail.RepoDetailFragment;
import com.reha.casestudy.feature.github.presentation.repolist.RepoListFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class, ViewModelModule.class, GithubModule.class})
public interface AppComponent {
    void inject(App app);
    void inject(RepoListFragment repoListFragment);
    void inject(RepoDetailFragment repoDetailFragment);

    Picasso getPicasso();

    GithubApiSearch getGithubSearchUseCase();
    GitHubRepository getGithubRepository();
    GithubRemoteDataSource getGithubRemoteDataSource();

}
