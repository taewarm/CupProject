package com.example.cupproject.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupproject.viewmodel.MainViewModel

class MainView(viewModel: MainViewModel) {
    private val viewModel = viewModel

    @Composable
    fun MainView(token: String) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TextButton(onClick = {
                viewModel.getUserInfo()
            }) {
                Text(text = "정보 가져오기")
            }
            TextButton(onClick = {
                viewModel.setUserInfo()
            }) {
                Text(text = "정보 생성하기")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreViewMainView() {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextButton(onClick = {

        }) {
            Text(text = "정보 가져오기")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "정보 생성하기")
        }
    }
}