package dao.cacao.feedapptest.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm

class AppLoader : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}