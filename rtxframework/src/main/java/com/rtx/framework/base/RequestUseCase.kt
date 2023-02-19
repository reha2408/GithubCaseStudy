package com.rtx.framework.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

abstract class RequestUseCase<R, Params> : BaseUseCase() {

    abstract fun buildUseCaseObservable(params: Params): Single<Response<R>>

    fun execute(observer: BaseDisposableObserver<R>, params: Params) {
        val progressLiveData = observer.baseViewModel.progressLiveData

        add(
            buildUseCaseObservable(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    progressLiveData.postValue(ResponseSubscriptionStatus.SUBSCRIBED)
                }
                .subscribeWith(observer)
        )
    }

    fun executeSilently(observer: BaseDisposableObserver<R>, params: Params) {
        add(
            buildUseCaseObservable(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(observer)
        )
    }
}
