package com.o5appstudio.pagingpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o5appstudio.pagingpractice.api.ApiResponse
import com.o5appstudio.pagingpractice.model.UserData
import com.o5appstudio.pagingpractice.model.Users
import com.o5appstudio.pagingpractice.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<ApiResponse<List<UserData>>>(ApiResponse.Loading())
    val users : StateFlow<ApiResponse<List<UserData>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers(){
        viewModelScope.launch {
            try {
                val response  = userRepository.getUsers()
                if(response.isSuccessful && response.body() != null){
//                    _users.value = ApiResponse.Success(response.body())
                    _users.emit(ApiResponse.Success(response.body()))
                } else {
//                    _users.value = ApiResponse.Error("Some Error")
                    _users.emit(ApiResponse.Error("Some Error"))

                }

            }
            catch (_: Exception){
                _users.emit(ApiResponse.Error("Some Error"))
            }
        }
    }

}