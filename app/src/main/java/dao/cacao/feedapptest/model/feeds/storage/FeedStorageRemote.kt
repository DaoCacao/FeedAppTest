package dao.cacao.feedapptest.model.feeds.storage

import dao.cacao.feedapptest.BuildConfig
import dao.cacao.feedapptest.model.entities.Feed
import dao.cacao.feedapptest.model.grpc.HeaderClientInterceptor
import io.grpc.ClientInterceptors
import io.grpc.ManagedChannelBuilder
import io.reactivex.Single
import social.ARMSocialServiceGrpc
import social.Social
import javax.inject.Inject


class FeedStorageRemote @Inject constructor() : RemoteStorage {

    private val channel by lazy {
        ManagedChannelBuilder
            .forTarget(BuildConfig.SCHEME + BuildConfig.SERVER_URL + BuildConfig.S3_PORT)
            .build()
            .let { ClientInterceptors.intercept(it, HeaderClientInterceptor()) }
    }

    private val stub by lazy { ARMSocialServiceGrpc.newBlockingStub(channel) }

    override fun getPosts(): Single<List<Feed>> {
        return getPostIds()
            .flatMap(this::getPosts)
            .map { it.postListList }
            .map { it.map(::Feed) }
    }

    //rpc GetGeneralFeedPostIds (SBaseRequest) returns (PostIdList);
    private fun getPostIds(): Single<Social.PostIdList> {
        return Single.just(Social.SBaseRequest.newBuilder().build())
            .map { stub.getGeneralFeedPostIds(it) }
    }

    //rpc GetPosts (GetPostsRequest) returns (PostList);
    private fun getPosts(ids: Social.PostIdList): Single<Social.PostList> {
        return Single.just(Social.GetPostsRequest.newBuilder().setIds(ids).build())
            .map { stub.getPosts(it) }
    }
}