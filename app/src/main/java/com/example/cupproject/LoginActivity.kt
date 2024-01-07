package com.example.cupproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupproject.ui.theme.CupProjectTheme
import com.kakao.sdk.user.UserApiClient

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White), contentAlignment = Alignment.Center
            ) {
                kakaoLoginButton(activity = this@LoginActivity)
            }
        }
    }
}

@Composable
fun kakaoLoginButton(activity: LoginActivity) {
    val context = LocalContext.current
    TextButton(onClick = {
        if (!UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            Toast.makeText(context, "KakaoTalk not install", Toast.LENGTH_SHORT).show()
            activity.finish()
            return@TextButton
        }
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            error?.let {
                Log.e(TAG, "login Fail ${it.message}", it)
                return@loginWithKakaoTalk
            }
            token?.let {
                Log.i(TAG, "login success ${it.accessToken}")
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("token", "${it.accessToken}")
                context.startActivity(intent)
            }
            Log.i(TAG, "$token $error ///")
        }
    }) {
        Text(text = "로그인버튼", color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginActivityPreview() {
    val context = LocalContext.current
    CupProjectTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White), contentAlignment = Alignment.Center
        ) {
            TextButton(onClick = {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    error?.let {
                        Log.e(TAG, "login Fail ${it.message}", it)
                        return@loginWithKakaoTalk
                    }
                    token?.let {
                        Log.i(TAG, "login success ${it.accessToken}")
                    }
                }
            }) {
                Text(text = "로그인버튼", color = Color.Black)
            }
        }
    }
}

const val TAG = "MainActivity"