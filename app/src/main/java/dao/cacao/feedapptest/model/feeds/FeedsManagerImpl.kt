package dao.cacao.feedapptest.model.feeds

import dao.cacao.feedapptest.model.entities.Feed
import dao.cacao.feedapptest.model.feeds.storage.LocalStorage
import dao.cacao.feedapptest.model.feeds.storage.RemoteStorage
import io.reactivex.Single
import javax.inject.Inject

class FeedsManagerImpl @Inject constructor() : FeedsManager {

    @Inject lateinit var local: LocalStorage
    @Inject lateinit var remote: RemoteStorage

    override fun getPosts(): Single<List<Feed>> {
        return remote.getPosts()
            .doOnSuccess(local::savePosts)
            .onErrorResumeNext(local.getPosts())
    }
}