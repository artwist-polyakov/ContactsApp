package com.master.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorld()
        }

    }

    @Composable
    private fun HelloWorld() {
        Text("Jetpack Compose")
    }

    @Preview(
        name = "portrait",
        showSystemUi = true,
    )
    @Composable
    private fun PreviewHelloWorld() {
        HelloWorld()
    }

}

