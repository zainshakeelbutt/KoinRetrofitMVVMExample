package com.o5appstudio.pagingpractice.api

import com.o5appstudio.pagingpractice.model.UserData
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {
    @GET("posts")
    suspend fun getUsersData() : Response<List<UserData>>
}