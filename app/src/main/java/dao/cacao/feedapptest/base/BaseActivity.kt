package dao.cacao.feedapptest.base

import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<P : MvpPresenter> : DaggerAppCompatActivity(), MvpView {

    @Inject lateinit var presenter: P

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}