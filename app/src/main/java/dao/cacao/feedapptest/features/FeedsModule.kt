package dao.cacao.feedapptest.features

import dagger.Binds
import dagger.Module
import dagger.Provides
import dao.cacao.feedapptest.features.adapter.FeedsAdapter

@Module
abstract class FeedsModule {

    @Binds
    abstract fun view(fragment: FeedsActivity): View

    @Binds
    abstract fun presenter(presenter: FeedsPresenter): Presenter

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun adapter() = FeedsAdapter()
    }
}