package dao.cacao.feedapptest.model.feeds

import dagger.Binds
import dagger.Module
import dao.cacao.feedapptest.model.feeds.storage.FeedStorageLocal
import dao.cacao.feedapptest.model.feeds.storage.FeedStorageRemote
import dao.cacao.feedapptest.model.feeds.storage.LocalStorage
import dao.cacao.feedapptest.model.feeds.storage.RemoteStorage

@Module
interface FeedsManagerModule {

    @Binds fun manager(manager: FeedsManagerImpl): FeedsManager

    @Binds fun local(storage: FeedStorageLocal): LocalStorage

    @Binds fun remote(storage: FeedStorageRemote): RemoteStorage
}