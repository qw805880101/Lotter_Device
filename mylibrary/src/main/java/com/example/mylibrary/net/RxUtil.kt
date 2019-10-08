package com.example.mylibrary.net

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RxUtil {

    companion object {
        /**
         * 统一线程处理
         *
         * @param <T>
         * @return
        </T> */
        fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {    //compose简化线程
            return ObservableTransformer { tObservable ->
                tObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        /**
         * 生成Observable
         *
         * @param <T>
         * @return
        </T> */
        fun <T> createData(t: T): Observable<T> {
            return Observable.create { subscriber ->
                try {
                    subscriber.onNext(t)
                    subscriber.onComplete()
                } catch (e: Exception) {
                    subscriber.onError(e)
                }
            }
        }
    }
}
