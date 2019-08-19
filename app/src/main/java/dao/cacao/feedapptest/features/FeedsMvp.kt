package dao.cacao.feedapptest.features

import dao.cacao.feedapptest.base.MvpPresenter
import dao.cacao.feedapptest.base.MvpView
import dao.cacao.feedapptest.model.entities.Feed


interface View : MvpView {
    fun showLoading()
    fun showFeeds(feeds: List<Feed>)
    fun showErrorMessage(it: Throwable)

    fun showFeed(feed: Feed)
}

interface Presenter : MvpPresenter {
    fun onViewInit()

    fun onFeedClick(feed: Feed)
}