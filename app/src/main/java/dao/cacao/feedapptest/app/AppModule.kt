package dao.cacao.feedapptest.app

import android.content.Context
import dagger.Module
import dagger.Provides
import dao.cacao.feedapptest.model.feeds.FeedsManagerModule

@Module(includes = [FeedsManagerModule::class])
class AppModule(private val context: Context) {

    @Provides
    fun context() = context
}