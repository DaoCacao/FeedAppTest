package dao.cacao.feedapptest.features

import android.os.Bundle
import dao.cacao.feedapptest.R
import dao.cacao.feedapptest.base.BaseActivity
import dao.cacao.feedapptest.extentions.gone
import dao.cacao.feedapptest.extentions.showToast
import dao.cacao.feedapptest.extentions.visible
import dao.cacao.feedapptest.features.adapter.FeedsAdapter
import dao.cacao.feedapptest.model.entities.Feed
import kotlinx.android.synthetic.main.activity_feeds.progressLoading
import kotlinx.android.synthetic.main.activity_feeds.recyclerFeeds
import javax.inject.Inject

class FeedsActivity : BaseActivity<Presenter>(), View {

    @Inject lateinit var adapter: FeedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)

        recyclerFeeds.adapter = adapter
        adapter.onClick = presenter::onFeedClick

        presenter.onViewInit()
    }

    override fun showLoading() {
        progressLoading.visible()
        recyclerFeeds.gone()
    }

    override fun showFeeds(feeds: List<Feed>) {
        progressLoading.gone()
        recyclerFeeds.visible()
        adapter.list = feeds
    }

    override fun showErrorMessage(it: Throwable) = showToast(it.localizedMessage)

    override fun showFeed(feed: Feed) = showToast(feed.toString())
}
