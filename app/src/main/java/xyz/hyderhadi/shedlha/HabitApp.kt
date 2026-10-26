package xyz.hyderhadi.shedlha

import android.app.Application
import xyz.hyderhadi.shedlha.di.AppModule
import xyz.hyderhadi.shedlha.di.AppModuleImpl

class HabitApp: Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}