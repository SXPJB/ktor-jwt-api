package com.fsociety.plugins

import com.fsociety.repository.UserPostgresqlRepository
import com.fsociety.repository.UserRepository
import com.fsociety.service.JwtService
import com.fsociety.service.UserPostgresqlService
import com.fsociety.service.UserService
import io.ktor.server.application.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

val appModule = module {
    singleOf(::UserRepository)
    single { UserService(get<UserRepository>()) }
    single { (application: Application) -> JwtService(application, get<UserService>()) }
    singleOf(::UserPostgresqlRepository)
    single { UserPostgresqlService(get<UserPostgresqlRepository>()) }
}

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
}