package com.rtx.framework.dummy

import com.rtx.framework.base.BaseDisposableObserver
import com.rtx.framework.base.BaseViewModel

class DummyDisposableObserver(baseViewModel: BaseViewModel) : BaseDisposableObserver<DummyResponse>(baseViewModel) {
    override fun onResponseSuccess(response: DummyResponse?) = Unit
}
