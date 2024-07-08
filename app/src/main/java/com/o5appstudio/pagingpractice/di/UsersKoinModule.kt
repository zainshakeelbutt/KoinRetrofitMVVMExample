package com.o5appstudio.pagingpractice.di

import com.o5appstudio.pagingpractice.api.UsersApi
import com.o5appstudio.pagingpractice.repository.UserRepository
import com.o5appstudio.pagingpractice.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(UsersApi::class.java)
    }
}

val repositoryModule = module {
    single {
        UserRepository(get())
    }
}

val viewModelModule = module {
    viewModel {
        UserViewModel(get())
    }
}

val allModules = listOf(networkModule, repositoryModule, viewModelModule)
