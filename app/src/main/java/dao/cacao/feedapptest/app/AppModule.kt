package dao.cacao.feedapptest.app

import android.content.Context
import dagger.Module
import dagger.Provides
import dao.cacao.feedapptest.model.feeds.FeedsManagerModule
import dao.cacao.feedapptest.model.grpc.GrpcGatewayModule

@Module(
    includes = [
        GrpcGatewayModule::class,
        FeedsManagerModule::class]
)
class AppModule(private val context: Context) {

    @Provides
    fun context() = context
}