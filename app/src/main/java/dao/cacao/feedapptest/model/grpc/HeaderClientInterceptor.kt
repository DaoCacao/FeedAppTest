package dao.cacao.feedapptest.model.grpc

import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.ClientCall
import io.grpc.ClientInterceptor
import io.grpc.ForwardingClientCall
import io.grpc.ForwardingClientCallListener
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import java.util.*

class HeaderClientInterceptor: ClientInterceptor {

    private val token by lazy { "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxNiwiaWF0IjoxNTY0NzMwMzExLCJleHAiOjE1OTU4MzQzMTF9.ppP_Nvvz9TSeQtmr5EtATJAjfQXuIsfiM4NkyLmR3_g" }
    private val session by lazy { UUID.randomUUID().toString() }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        method: MethodDescriptor<ReqT, RespT>?,
        callOptions: CallOptions?,
        next: Channel?
    ): ClientCall<ReqT, RespT> {
        return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next?.newCall(method, callOptions)) {

            override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                val metadate = Metadata()
                val tokenKey = Metadata.Key.of("token", Metadata.ASCII_STRING_MARSHALLER)
                val sessionKey = Metadata.Key.of("session", Metadata.ASCII_STRING_MARSHALLER)
                metadate.put(tokenKey, token)
                metadate.put(sessionKey, session)

                headers.merge(metadate)

                super.start(object : ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {}, headers)
            }
        }
    }
}