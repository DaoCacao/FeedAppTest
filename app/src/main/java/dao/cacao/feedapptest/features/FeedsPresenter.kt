package dao.cacao.feedapptest.features

import dao.cacao.feedapptest.base.BasePresenter
import dao.cacao.feedapptest.extentions.scheduleIo
import dao.cacao.feedapptest.model.entities.Feed
import dao.cacao.feedapptest.model.feeds.FeedsManager
import javax.inject.Inject

class FeedsPresenter @Inject constructor() : BasePresenter<View>(), Presenter {

    @Inject lateinit var feedsManager: FeedsManager

    override fun onViewInit() {
        feedsManager.loadPosts()
            .scheduleIo()
            .doOnError { view.showErrorMessage(it) }
            .onErrorResumeNext(feedsManager.getCashedPosts())
            .doOnSubscribe { view.showLoading() }
            .map { it.sortedBy { it.created } }
            .subscribe(view::showFeeds)
            .autoDispose()
    }

    override fun onFeedClick(feed: Feed) = view.showFeed(feed)
}