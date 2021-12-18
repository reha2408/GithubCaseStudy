package com.rtx.framework.dummy

import com.rtx.framework.base.RequestUseCase
import io.reactivex.Single
import retrofit2.Response

class DummyRequestUseCase : RequestUseCase<DummyResponse, DummyParams>() {

    override fun buildUseCaseObservable(params: DummyParams): Single<Response<DummyResponse>> {
        val response = Response.success(DummyResponse())
        return Single.just(response)
    }
}
