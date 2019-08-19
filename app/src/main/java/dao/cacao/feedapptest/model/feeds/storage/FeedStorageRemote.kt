package dao.cacao.feedapptest.model.feeds.storage

import dao.cacao.feedapptest.BuildConfig
import dao.cacao.feedapptest.model.entities.Feed
import io.grpc.ManagedChannelBuilder
import io.grpc.Metadata
import io.grpc.stub.MetadataUtils
import io.grpc.stub.StreamObserver
import io.reactivex.Single
import io.reactivex.SingleEmitter
import social.ARMSocialServiceGrpc
import social.Social
import java.util.*
import javax.inject.Inject

class FeedStorageRemote @Inject constructor() : RemoteStorage {

    private val channel by lazy {
        ManagedChannelBuilder
            .forAddress(BuildConfig.HOST, BuildConfig.PORT)
            .usePlaintext()
            .build()
    }

    private val metadata by lazy {
        Metadata().apply {
            val tokenKey = Metadata.Key.of("token", Metadata.ASCII_STRING_MARSHALLER)
            val sessionKey = Metadata.Key.of("session", Metadata.ASCII_STRING_MARSHALLER)
            put(tokenKey, BuildConfig.TOKEN)
            put(sessionKey, UUID.randomUUID().toString())
        }
    }

    private val stub by lazy { MetadataUtils.attachHeaders(ARMSocialServiceGrpc.newStub(channel), metadata) }

    override fun getPosts(): Single<List<Feed>> {
        return getPostIds()
            .flatMap(this::getPosts)
            .map { it.postListList.map(::Feed) }
    }

    //rpc GetGeneralFeedPostIds (SBaseRequest) returns (PostIdList);
    private fun getPostIds(): Single<Social.PostIdList> {
        println("getPostIds")
        return Single.create {
            stub.getGeneralFeedPostIds(Social.SBaseRequest.newBuilder().build(), observeStream(it))
        }
    }

    //rpc GetPosts (GetPostsRequest) returns (PostList);
    private fun getPosts(ids: Social.PostIdList): Single<Social.PostList> {
        println("loadPosts")
        return Single.create {
            stub.getPosts(Social.GetPostsRequest.newBuilder().setIds(ids).build(), observeStream(it))
        }
    }

    private fun <T> observeStream(emitter: SingleEmitter<T>): StreamObserver<T> {
        return object : StreamObserver<T> {
            override fun onNext(value: T?) {
                println("onNext $value")
                emitter.onSuccess(value!!)
            }

            override fun onError(t: Throwable?) {
                println("onError $t")
                emitter.onError(t!!)
            }

            override fun onCompleted() {
                println("onCompleted")
            }
        }
    }
}