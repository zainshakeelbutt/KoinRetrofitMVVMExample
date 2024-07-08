package com.o5appstudio.pagingpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.o5appstudio.pagingpractice.api.ApiResponse
import com.o5appstudio.pagingpractice.model.UserData
import com.o5appstudio.pagingpractice.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    Surface {
        Scaffold (modifier = Modifier.fillMaxSize()){

            val viewModel : UserViewModel = koinViewModel()
            val userList = viewModel.users.collectAsState()

            when(userList.value){
                is ApiResponse.Error -> {
                    Box (modifier = Modifier.fillMaxSize().padding(it)){
                        Text(text = "Some Error", modifier = Modifier.align(Alignment.Center))
                    }
                }
                is ApiResponse.Loading -> {
                    Box (modifier = Modifier.fillMaxSize().padding(it)){
                        Text(text = "Loading....", modifier = Modifier.align(Alignment.Center))
                    }
                }
                is ApiResponse.Success -> {
                    userList.value.data?.let { it1 ->
                        UsersList(modifier = Modifier.padding(it),
                            it1
                        )
                    }
                }
            }





        }
    }
}

@Composable
fun UsersList(modifier: Modifier = Modifier, userList: List<UserData>) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState, enabled = true)
            .fillMaxSize()
            .padding(16.dp)

    ) {
        userList.forEach {
            Text(text = it.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
    
}

