package dao.cacao.feedapptest.extentions

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.saveSubscribe(onSuccess: (T) -> Unit) = this.subscribe(onSuccess, { Log.e("ERROR", it.message) })
fun Completable.saveSubscribe(onSuccess: () -> Unit) = this.subscribe(onSuccess, { Log.e("ERROR", it.message) })

fun <T> Single<T>.scheduleIo() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun Completable.scheduleIo() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())