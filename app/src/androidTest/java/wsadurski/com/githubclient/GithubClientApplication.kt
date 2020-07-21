package wsadurski.com.githubclient

import android.app.Application
import wsadurski.com.githubclient.data.di.DataModule
import wsadurski.com.githubclient.di.AppComponent
import wsadurski.com.githubclient.di.DaggerAppComponent

class GithubClientApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .dataModule(DataModule())
            .repositoriesModule(MockRepositoriesModule(this))
            .build()
    }
}