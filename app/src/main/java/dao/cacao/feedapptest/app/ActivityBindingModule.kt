package dao.cacao.feedapptest.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dao.cacao.feedapptest.features.FeedsActivity
import dao.cacao.feedapptest.features.FeedsModule

@Module
@Suppress("Unused")
interface ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FeedsModule::class]) fun main(): FeedsActivity
}