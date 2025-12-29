package com.wemade.kmp.rocket.di

import com.wemade.kmp.rocket.RocketRepository
import com.wemade.kmp.rocket.repository.RocketRepositoryImpl
import com.wemade.kmp.rocket.screens.detail.RocketDetailViewModel
import com.wemade.kmp.rocket.screens.list.RocketListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }
    single<RocketRepository> { RocketRepositoryImpl() }
}

val viewModelModule = module {
    factoryOf(::RocketListViewModel)
    viewModel { (id: String) ->
        RocketDetailViewModel(
            launchId = id,
            repository = get()
        )
    }
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
        )
    }
}
