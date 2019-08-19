package dao.cacao.feedapptest.model.feeds

import dao.cacao.feedapptest.model.entities.Feed
import io.reactivex.Single

interface FeedsManager {
    fun getPosts(): Single<List<Feed>>
}