package com.example.mylibrary

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class RxManager {

    private val mCompositeSubscription = CompositeDisposable()// 管理订阅者者

    fun add(m: Disposable) {
        mCompositeSubscription.add(m)
    }

    fun clear() {
        mCompositeSubscription.dispose()// 取消订阅
    }


}