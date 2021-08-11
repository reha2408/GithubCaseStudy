package com.reha.casestudy.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

abstract class RequestUseCase<R, Params> : BaseUseCase() {

    abstract fun  buildUseCaseObservable(params:Params): Single<Response<R>>

    fun execute(observer: BaseDisposableObserver<R>, params: Params) {
        val progressLiveData = observer.baseViewModel.progressLiveData

        add(buildUseCaseObservable(params)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                progressLiveData.postValue(ResponseSubscriptionStatus.SUBSCRIBED)
            }
            .doAfterTerminate {
                progressLiveData.postValue(ResponseSubscriptionStatus.FINISHED)
            }
            .subscribeWith(observer)
        )
    }
}