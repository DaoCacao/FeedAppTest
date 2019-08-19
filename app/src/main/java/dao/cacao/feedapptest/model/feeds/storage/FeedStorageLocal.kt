package dao.cacao.feedapptest.model.feeds.storage

import dao.cacao.feedapptest.model.entities.Feed
import io.reactivex.Single
import io.realm.Realm
import javax.inject.Inject

class FeedStorageLocal @Inject constructor() : LocalStorage {

    private val realm by lazy { Realm.getDefaultInstance() }

    override fun getPosts(): Single<List<Feed>> = Single.just(realm.where(Feed::class.java).findAll().toList())

    override fun savePosts(posts: List<Feed>) = realm.executeTransaction { it.copyToRealmOrUpdate(posts) }
}