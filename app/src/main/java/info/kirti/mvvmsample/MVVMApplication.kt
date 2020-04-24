package info.kirti.mvvmsample

import android.app.Application
import info.kirti.mvvmsample.data.db.AppDatabase
import info.kirti.mvvmsample.data.network.MyApi
import info.kirti.mvvmsample.data.network.NetworkConnectionInterceptor
import info.kirti.mvvmsample.data.prefrences.PrefrenceProvider
import info.kirti.mvvmsample.data.repositries.QuotesRepository
import info.kirti.mvvmsample.data.repositries.UserRepository
import info.kirti.mvvmsample.ui.auth.AuthViewModelFactory
import info.kirti.mvvmsample.ui.home.profile.ProfileViewModelFactory
import info.kirti.mvvmsample.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(),KodeinAware {
    override val kodein= Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind() from singleton {NetworkConnectionInterceptor(instance())}
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PrefrenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from singleton { QuotesRepository(instance(),instance(),instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
        //bind() from provider { CameraXViewModelFactory(instance()) }
    }

}