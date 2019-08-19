package dao.cacao.feedapptest.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BasePresenter<V : MvpView> : MvpPresenter {

    @Inject lateinit var view: V

    private var disposables: CompositeDisposable? = null

    override fun onViewDestroyed() {
        disposables?.dispose()
        disposables = null
    }

    protected fun Disposable.autoDispose(): Disposable {
        return apply {
            if (disposables == null) disposables = CompositeDisposable()
            disposables?.add(this)
        }
    }
}