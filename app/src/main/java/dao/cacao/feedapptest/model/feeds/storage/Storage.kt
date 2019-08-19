package dao.cacao.feedapptest.model.feeds.storage

import dao.cacao.feedapptest.model.entities.Feed
import io.reactivex.Single

interface LocalStorage {
    fun getPosts(): Single<List<Feed>>

    fun savePosts(posts: List<Feed>)
}

interface RemoteStorage {
    fun getPosts(): Single<List<Feed>>

}
