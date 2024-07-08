package com.o5appstudio.pagingpractice.repository

import com.o5appstudio.pagingpractice.api.UsersApi
import com.o5appstudio.pagingpractice.model.UserData
import com.o5appstudio.pagingpractice.model.Users
import retrofit2.Response

class UserRepository(private val usersApi: UsersApi) {


    suspend fun getUsers() : Response<List<UserData>> {
        return usersApi.getUsersData()
    }



}