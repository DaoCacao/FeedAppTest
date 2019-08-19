package dao.cacao.feedapptest.model.grpc

import dagger.Module
import dagger.Provides
import dao.cacao.feedapptest.BuildConfig
import io.grpc.CallCredentials
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.Metadata
import io.grpc.Status
import social.ARMSocialServiceGrpc
import java.util.*
import java.util.concurrent.Executor

@Module
class GrpcGatewayModule {

    @Provides
    fun socialStub(
        channel: ManagedChannel,
        credentials: CallCredentials
    ): ARMSocialServiceGrpc.ARMSocialServiceStub {
        return ARMSocialServiceGrpc.newStub(channel).withCallCredentials(credentials)

    }

    @Provides
    fun socialStubBlocking(
        channel: ManagedChannel,
        credentials: CallCredentials
    ): ARMSocialServiceGrpc.ARMSocialServiceBlockingStub {
        return ARMSocialServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    }

    @Provides
    fun channel(metadata: Metadata): ManagedChannel {
        return ManagedChannelBuilder
            .forAddress(BuildConfig.HOST, BuildConfig.PORT)
//            .intercept(MetadataUtils.newAttachHeadersInterceptor(metadata)) FIXME instead of stub.withCallCredentials()
            .maxInboundMessageSize(1000 * 1024 * 1024)
            .maxInboundMetadataSize(1000 * 1024 * 1024)
            .usePlaintext()
            .build()
    }

    @Provides
    fun metadata(): Metadata {
        return Metadata().apply {
            val tokenKey = Metadata.Key.of("token", Metadata.ASCII_STRING_MARSHALLER)
            val sessionKey = Metadata.Key.of("session", Metadata.ASCII_STRING_MARSHALLER)
            put(tokenKey, BuildConfig.TOKEN)
            put(sessionKey, UUID.randomUUID().toString())
        }
    }

    @Provides
    fun callCredentials(metadata: Metadata): CallCredentials {
        return object : CallCredentials() {
            override fun applyRequestMetadata(request: RequestInfo, executor: Executor, applier: MetadataApplier) {
                executor.execute {
                    try {
                        applier.apply(metadata)
                    } catch (e: Throwable) {
                        applier.fail(Status.UNAUTHENTICATED.withCause(e))
                    }
                }
            }

            override fun thisUsesUnstableApi() {}
        }
    }
}