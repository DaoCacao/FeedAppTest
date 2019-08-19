package dao.cacao.feedapptest.model.feeds.storage

import dao.cacao.feedapptest.model.entities.Feed
import io.grpc.stub.StreamObserver
import io.reactivex.Single
import io.reactivex.SingleEmitter
import social.ARMSocialServiceGrpc
import social.Social
import javax.inject.Inject


class FeedStorageRemote @Inject constructor() : RemoteStorage {

    @Inject lateinit var socialStub: ARMSocialServiceGrpc.ARMSocialServiceStub

    override fun getPosts(): Single<List<Feed>> {
        return getPostIds()
            .flatMap(this::getPosts)
            .map { it.postListList.map(::Feed) }
    }

    //rpc GetGeneralFeedPostIds (SBaseRequest) returns (PostIdList);
    private fun getPostIds(): Single<Social.PostIdList> {
        println("getPostIds")
        return Single.create {
            socialStub.getGeneralFeedPostIds(Social.SBaseRequest.newBuilder().build(), observeStream(it))
        }
    }

    //rpc GetPosts (GetPostsRequest) returns (PostList);
    private fun getPosts(ids: Social.PostIdList): Single<Social.PostList> {
        println("getPosts")
        return Single.create {
            socialStub.getPosts(Social.GetPostsRequest.newBuilder().setIds(ids).build(), observeStream(it))
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